package com.example.recipe_app.app_ui.viewModel.drinks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.recipe_app.data.cocktailService
import com.example.recipe_app.models.AlcoholicDrinks
import kotlinx.coroutines.launch

class AlcoholicDrinksViewModel: ViewModel() {

    data class AlcoholicDrinksRecipeState(
        val loading: Boolean = true, // Indicates whether data is being loaded; defaults to true.
        val list: List<AlcoholicDrinks> = emptyList(), // A list of categories, defaults to an empty list.
        val error: String? = null // An error message, defaults to null indicating no error.
    )

    private val _alcoholicDrinksState = mutableStateOf(AlcoholicDrinksRecipeState())

    val alcoholicDrinksState: State<AlcoholicDrinksRecipeState> = _alcoholicDrinksState

    init {
        fetchAlcoholicDrinks()
    }

    private fun fetchAlcoholicDrinks() {
        viewModelScope.launch {
            try {
                // Attempts to fetch categories from the recipe service.
                val response = cocktailService.getAlcoholicDrinks()

                // Updates the state with the new list of categories, sets loading to false, and clears any errors.
                _alcoholicDrinksState.value = _alcoholicDrinksState.value.copy(
                    list = response.alcoholicDrinksList,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                // If an error occurs, updates the state to stop loading and sets the error message.
                _alcoholicDrinksState.value = _alcoholicDrinksState.value.copy(
                    loading = false,
                    error = "error fetching categories ${e.message}"
                )
            }
        }
    }

}