package com.example.recipe_app.data

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.recipe_app.app_ui.viewModel.CountryViewModel.RestCountryState
import com.google.gson.annotations.SerializedName



private val _restCountryState = mutableStateOf(RestCountryState())

val restCountriesState: State<RestCountryState> = _restCountryState

data class RestCountry(
    @SerializedName("name") val name: Name,
    @SerializedName("cca2") val alpha2Code: String,
    @SerializedName("cca3") val alpha3Code: String,
    @SerializedName("capital") val capital: List<String>?,
    @SerializedName("region") val region: String,
    @SerializedName("subregion") val subregion: String?,
    @SerializedName("population") val population: Long,
    @SerializedName("area") val area: Double?,
    @SerializedName("flags") val flags: Flags
) {
    data class Name(
        @SerializedName("common") val common: String,
        @SerializedName("official") val official: String
    )

    data class Flags(
        @SerializedName("svg") val svg: String, // URL para a bandeira em formato SVG
        @SerializedName("png") val png: String  // URL para a bandeira em formato PNG
    )
}

private suspend fun fetchRestCountries() {

    try {
            val restCountriesResponse = restCountryRecipeService.getRestCountries()

            val restCountries = restCountriesResponse ?: emptyList()

            _restCountryState.value = _restCountryState.value.copy(
                loading = false,
                list = restCountries,
                error = null
            )
            Log.d("CountryRepository", "Número de países com bandeiras: ${restCountries.size}")

        } catch (e: Exception) {
            _restCountryState.value = _restCountryState.value.copy(
                loading = false,
                list = emptyList(),
                error = null
            )
        }
    }

