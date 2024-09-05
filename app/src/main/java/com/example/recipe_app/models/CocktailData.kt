package com.example.recipe_app.models

import com.google.gson.annotations.SerializedName

data class AlcoholicDrinks(
    @SerializedName("strDrink")    val strDrink: String,
    @SerializedName("strDrinkThumb")  val strDrinkThumb: String,
    @SerializedName("idDrink")  val idDrink: String
)

data class AlcoholicDrinksResponse(@SerializedName("drinks") val alcoholicDrinksList: List<AlcoholicDrinks>)