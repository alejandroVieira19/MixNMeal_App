package com.example.recipe_app.models

import com.google.gson.annotations.SerializedName

data class AlcoholicDrinks(
    @SerializedName("strDrink")    val strDrink: String,
    @SerializedName("strDrinkThumb")  val strDrinkThumb: String,
    @SerializedName("idDrink")  val idDrink: String
)

data class RandomDrink(
    @SerializedName("idDrink") val idDrink: String,
    @SerializedName("strDrink") val strDrink: String,
    @SerializedName("strDrinkAlternate") val strDrinkAlternate: String?,
    @SerializedName("strTags") val strTags: String?,
    @SerializedName("strVideo") val strVideo: String?,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strIBA") val strIBA: String?,
    @SerializedName("strAlcoholic") val strAlcoholic: String,
    @SerializedName("strGlass") val strGlass: String,
    @SerializedName("strInstructions") val strInstructions: String,
    @SerializedName("strInstructionsES") val strInstructionsES: String?,
    @SerializedName("strInstructionsDE") val strInstructionsDE: String?,
    @SerializedName("strInstructionsFR") val strInstructionsFR: String?,
    @SerializedName("strInstructionsIT") val strInstructionsIT: String?,
    @SerializedName("strInstructionsZH-HANS") val strInstructionsZH_HANS: String?,
    @SerializedName("strInstructionsZH-HANT") val strInstructionsZH_HANT: String?,
    @SerializedName("strDrinkThumb") val strDrinkThumb: String?,
    @SerializedName("strIngredient1") val strIngredient1: String?,
    @SerializedName("strIngredient2") val strIngredient2: String?,
    @SerializedName("strIngredient3") val strIngredient3: String?,
    @SerializedName("strIngredient4") val strIngredient4: String?,
    @SerializedName("strIngredient5") val strIngredient5: String?,
    @SerializedName("strIngredient6") val strIngredient6: String?,
    @SerializedName("strIngredient7") val strIngredient7: String?,
    @SerializedName("strIngredient8") val strIngredient8: String?,
    @SerializedName("strIngredient9") val strIngredient9: String?,
    @SerializedName("strIngredient10") val strIngredient10: String?,
    @SerializedName("strIngredient11") val strIngredient11: String?,
    @SerializedName("strIngredient12") val strIngredient12: String?,
    @SerializedName("strIngredient13") val strIngredient13: String?,
    @SerializedName("strIngredient14") val strIngredient14: String?,
    @SerializedName("strMeasure1") val strMeasure1: String?,
    @SerializedName("strMeasure2") val strMeasure2: String?,
    @SerializedName("strMeasure3") val strMeasure3: String?,
    @SerializedName("strMeasure4") val strMeasure4: String?,
    @SerializedName("strMeasure5") val strMeasure5: String?,
    @SerializedName("strMeasure6") val strMeasure6: String?,
    @SerializedName("strMeasure7") val strMeasure7: String?
)


data class AlcoholicDrinksResponse(@SerializedName("drinks") val alcoholicDrinksList: List<AlcoholicDrinks>)

data class RandomDrinkResponse(@SerializedName("drinks") val randomDrinkList: List<RandomDrink>)