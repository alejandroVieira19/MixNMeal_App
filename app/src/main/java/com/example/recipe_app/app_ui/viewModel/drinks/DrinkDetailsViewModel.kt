package com.example.recipe_app.app_ui.viewModel.drinks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.cocktailService
import com.example.recipe_app.models.DrinkDetail
import kotlinx.coroutines.launch

class DrinkDetailsViewModel: ViewModel() {

    data class DrinkDetailState(
        val loading: Boolean = true, // Indicates whether data is being loaded; defaults to true.
        val list: List<DrinkDetail> = emptyList(), // A list of categories, defaults to an empty list.
        val error: String? = null // An error message, defaults to null indicating no error.
    )

    private val _drinkDetailState = mutableStateOf(DrinkDetailState())

    val drinkDetailState: State<DrinkDetailState> = _drinkDetailState

    fun selectedDrink(drinkId: String) {
        fetchDrinkDetails(drinkId)
    }

    private fun fetchDrinkDetails(drinkId: String) {
        viewModelScope.launch {
            try {
                // Attempts to fetch categories from the recipe service.
                val response = cocktailService.getDrinkFromId(drinkId)

                // Updates the state with the new list of categories, sets loading to false, and clears any errors.
                _drinkDetailState.value = _drinkDetailState.value.copy(
                    list = response.drinkDetailList,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                // If an error occurs, updates the state to stop loading and sets the error message.
                _drinkDetailState.value = _drinkDetailState.value.copy(
                    loading = false,
                    error = "error fetching categories ${e.message}"
                )
            }
        }


    }
}