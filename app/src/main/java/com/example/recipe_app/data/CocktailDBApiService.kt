package com.example.recipe_app.data

import com.example.recipe_app.models.AlcoholicDrinksResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val _CocktailApiRetrofit = Retrofit.Builder().baseUrl("https://www.thecocktaildb.com/api/json/v1/1/").addConverterFactory(
    GsonConverterFactory.create()).build()

val cocktailService = _CocktailApiRetrofit.create(CocktailDBApiServiceInterface::class.java)

interface CocktailDBApiServiceInterface {
    @GET("filter.php?a=Alcoholic")

    suspend fun getAlcoholicDrinks(): AlcoholicDrinksResponse
}