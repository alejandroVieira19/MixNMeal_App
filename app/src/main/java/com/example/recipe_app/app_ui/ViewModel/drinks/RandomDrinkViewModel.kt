package com.example.recipe_app.app_ui.ViewModel.drinks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.cocktailService
import com.example.recipe_app.models.RandomDrink
import kotlinx.coroutines.launch

class RandomDrinkViewModel: ViewModel() {

    data class RandomDrinkState(
        val loading: Boolean = true, // Indicates whether data is being loaded; defaults to true.
        val list: List<RandomDrink> = emptyList(), // A list of categories, defaults to an empty list.
        val error: String? = null // An error message, defaults to null indicating no error.
    )

    private val _randomDrinkState = mutableStateOf(RandomDrinkState())

    val randomDrinkState: State<RandomDrinkState> = _randomDrinkState

    init {
        fetchRandomDrink()
    }

    private fun fetchRandomDrink() {
        viewModelScope.launch {
            try {
                // Attempts to fetch categories from the recipe service.
                val response = cocktailService.getRandomDrink()

                // Updates the state with the new list of categories, sets loading to false, and clears any errors.
                _randomDrinkState.value = _randomDrinkState.value.copy(
                    list = response.randomDrinkList,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                // If an error occurs, updates the state to stop loading and sets the error message.
                _randomDrinkState.value = _randomDrinkState.value.copy(
                    loading = false,
                    error = "error fetching categories ${e.message}"
                )
            }
        }
    }
}