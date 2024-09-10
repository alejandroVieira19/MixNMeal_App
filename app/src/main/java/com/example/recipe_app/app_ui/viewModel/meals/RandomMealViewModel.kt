package com.example.recipe_app.app_ui.viewModel.meals

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.recipeService
import com.example.recipe_app.models.RandomMeal
import kotlinx.coroutines.launch

class RandomMealViewModel: ViewModel() {

    data class RandomMealState(
        val loading: Boolean = true, // Indicates whether data is being loaded; defaults to true.
        val list: List<RandomMeal> = emptyList(), // A list of categories, defaults to an empty list.
        val error: String? = null // An error message, defaults to null indicating no error.
    )

    private val _randomMealState = mutableStateOf(RandomMealState())

    val randomMealState: State<RandomMealState> = _randomMealState

    init {
        fetchRandomMeal()
    }

    private fun fetchRandomMeal() {
        viewModelScope.launch {
            try {
                // Attempts to fetch categories from the recipe service.
                val response = recipeService.getRandomMeal()

                // Updates the state with the new list of categories, sets loading to false, and clears any errors.
                _randomMealState.value = _randomMealState.value.copy(
                    list = response.randomMealInfo,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                // If an error occurs, updates the state to stop loading and sets the error message.
                _randomMealState.value = _randomMealState.value.copy(
                    loading = false,
                    error = "error fetching categories ${e.message}"
                )
            }
        }
    }
}
