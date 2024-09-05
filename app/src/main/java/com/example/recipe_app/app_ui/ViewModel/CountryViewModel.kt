package com.example.recipe_app.app_ui.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.models.Country
import com.example.recipe_app.data.RestCountry
import com.example.recipe_app.data.recipeService
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {

    private val _countryState = mutableStateOf(CountryState())


    val countriesState: State<CountryState> = _countryState


    data class CountryState(
        val loading: Boolean = true,
        val list: List<Country> = emptyList(),
        val error: String? = null
    )

    data class RestCountryState(
        val loading: Boolean = true,
        val list: List<RestCountry> = emptyList(),
        val error: String? = null
    )


    init {
       fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCountries()

                val countries = response.countryList ?: emptyList() // Ensure this is never null
                _countryState.value = _countryState.value.copy(
                    loading = false,
                    list = countries,
                    error = null
                )
            } catch (e: Exception) {
                _countryState.value = _countryState.value.copy(
                    loading = false,
                    list = emptyList(), // Ensure this is never null
                    error = "Error fetching countries: ${e.message}"
                )
            }
        }
    }
}