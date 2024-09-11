package com.example.recipe_app.data

import com.example.recipe_app.models.DrinksResponse
import com.example.recipe_app.models.DrinkDetailResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val _CocktailApiRetrofit = Retrofit.Builder().baseUrl("https://www.thecocktaildb.com/api/json/v1/1/").addConverterFactory(
    GsonConverterFactory.create()).build()

val cocktailService = _CocktailApiRetrofit.create(CocktailDBApiServiceInterface::class.java)

interface CocktailDBApiServiceInterface {
    @GET("filter.php?a=Alcoholic")
    suspend fun getAlcoholicDrinks(): DrinksResponse

    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicDrinks(): DrinksResponse

    @GET("random.php")
    suspend fun getRandomDrink(): DrinkDetailResponse

    @GET("lookup.php?")
    suspend fun getDrinkFromId(@Query("i") drinkId: String): DrinkDetailResponse
}
