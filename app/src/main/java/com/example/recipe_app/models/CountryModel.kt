package com.example.recipe_app.models
import com.google.gson.annotations.SerializedName


// Data class to represent each country or area
data class Country(
    @SerializedName("strArea") val strArea: String
)

// Data class to represent the response containing a list of countries or areas
data class CountryResponse(
    @SerializedName("meals") val countryList: List<Country>
)




