package com.example.recipe_app

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    // Private mutable state that holds the current RecipeState.
    // This is used internally to update the state.
    private val _categoryState = mutableStateOf(RecipeState())

    // Public immutable state that other classes can observe.
    // Exposes _categoryState as a read-only State.
    val categoriesState: State<RecipeState> = _categoryState

    // Data class representing the state of the recipes, including loading, list of categories, and an error message.
    data class RecipeState(
        val loading: Boolean = true, // Indicates whether data is being loaded; defaults to true.
        val list: List<Category> = emptyList(), // A list of categories, defaults to an empty list.
        val error: String? = null // An error message, defaults to null indicating no error.
    )


    init {
        fetchCategories()
    }

    private fun fetchCategories() {

        // Launches a coroutine in the ViewModel's scope.
        viewModelScope.launch {
            try {
                // Attempts to fetch categories from the recipe service.
                val response = recipeService.getCategories()

                // Updates the state with the new list of categories, sets loading to false, and clears any errors.
                _categoryState.value = _categoryState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                // If an error occurs, updates the state to stop loading and sets the error message.
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "error fetching categories ${e.message}"
                )
            }
        }
    }
}



