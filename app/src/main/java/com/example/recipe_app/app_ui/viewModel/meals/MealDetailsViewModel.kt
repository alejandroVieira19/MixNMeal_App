package com.example.recipe_app.app_ui.viewModel.meals

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.recipeService
import com.example.recipe_app.models.MealDetails
import kotlinx.coroutines.launch

class MealDetailsViewModel: ViewModel() {
    data class MealDetailState(
        val loading: Boolean = true, // Indicates whether data is being loaded; defaults to true.
        val list: List<MealDetails> = emptyList(), // A list of categories, defaults to an empty list.
        val error: String? = null // An error message, defaults to null indicating no error.
    )

    private val _mealDetailState = mutableStateOf(MealDetailState())

    val mealDetailState: State<MealDetailState> = _mealDetailState

    fun selectedMeal(mealId: String) {
        fetchMealDetails(mealId)
    }

    private fun fetchMealDetails(mealId: String) {
        viewModelScope.launch {
            try {
                // Attempts to fetch categories from the recipe service.
                val response = recipeService.getMealDetailFromId(mealId)

                // Updates the state with the new list of categories, sets loading to false, and clears any errors.
                _mealDetailState.value = _mealDetailState.value.copy(
                    list = response.mealDetailsInfo,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                // If an error occurs, updates the state to stop loading and sets the error message.
                _mealDetailState.value = _mealDetailState.value.copy(
                    loading = false,
                    error = "error fetching categories ${e.message}"
                )
            }
        }

    }

}