package com.example.recipe_app.app_ui.viewModel.meals

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.recipeService
import com.example.recipe_app.models.Meal
import kotlinx.coroutines.launch

class MealsByChosenCountryViewModel: ViewModel() {

    class MealsByChosenCountryState : ViewModel() {
        data class AllMealsByCategoryChosenState(
            val loading: Boolean = true, // Indicates whether data is being loaded; defaults to true.
            val list: List<Meal> = emptyList(), // A list of categories, defaults to an empty list.
            val error: String? = null // An error message, defaults to null indicating no error.
        )

        private val _allMealsByChosenCountryState = mutableStateOf(AllMealsByCategoryChosenState())


        val allMealsByChosenCountryState: State<AllMealsByCategoryChosenState> =
            _allMealsByChosenCountryState

        fun selectChosenCountry(country: String) {
            fetchAllMealChosenCountry(country)
        }

        private fun fetchAllMealChosenCountry(country: String) {

            viewModelScope.launch {
                try {
                    // Attempts to fetch categories from the recipe service.
                    val response = recipeService.getMealsFromChosenCountry(country)

                    // Updates the state with the new list of categories, sets loading to false, and clears any errors.
                    _allMealsByChosenCountryState.value = _allMealsByChosenCountryState.value.copy(
                        list = response.meals,
                        loading = false,
                        error = null
                    )

                } catch (e: Exception) {
                    // If an error occurs, updates the state to stop loading and sets the error message.
                    _allMealsByChosenCountryState.value = _allMealsByChosenCountryState.value.copy(
                        loading = false,
                        error = "error fetching categories ${e.message}"
                    )
                }
            }
        }

    }
}
