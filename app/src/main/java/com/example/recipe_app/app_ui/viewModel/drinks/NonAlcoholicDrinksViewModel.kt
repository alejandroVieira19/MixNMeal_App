package com.example.recipe_app.app_ui.viewModel.drinks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.cocktailService
import com.example.recipe_app.models.Drinks
import kotlinx.coroutines.launch

class NonAlcoholicDrinksViewModel: ViewModel() {

    data class NonAlcoholicDrinksState(
        val loading: Boolean = true, // Indicates whether data is being loaded; defaults to true.
        val list: List<Drinks> = emptyList(), // A list of categories, defaults to an empty list.
        val error: String? = null // An error message, defaults to null indicating no error.
    )

    private val _nonAlcoholicDrinksState = mutableStateOf(NonAlcoholicDrinksState())

    val nonAlcoholicDrinksState: State<NonAlcoholicDrinksState> = _nonAlcoholicDrinksState

    init {
        fetchNonAlcoholicDrinks()
    }

    private fun fetchNonAlcoholicDrinks() {
        viewModelScope.launch {
            try {
                // Attempts to fetch categories from the recipe service.
                val response = cocktailService.getNonAlcoholicDrinks()

                // Updates the state with the new list of categories, sets loading to false, and clears any errors.
                _nonAlcoholicDrinksState.value = _nonAlcoholicDrinksState.value.copy(
                    list = response.drinksList,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                // If an error occurs, updates the state to stop loading and sets the error message.
                _nonAlcoholicDrinksState.value = _nonAlcoholicDrinksState.value.copy(
                    loading = false,
                    error = "error fetching categories ${e.message}"
                )
            }
        }
    }
}