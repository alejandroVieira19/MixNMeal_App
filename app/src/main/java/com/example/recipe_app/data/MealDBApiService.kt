package com.example.recipe_app.data


import androidx.compose.runtime.mutableStateOf
import com.example.recipe_app.models.CategoriesResponse
import com.example.recipe_app.models.CountryResponse
import com.example.recipe_app.models.MealsResponse
import com.example.recipe_app.models.MealDetailResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


// Configures the Retrofit instance for communication with the internet
private val _retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build()

// Creates a service using the Retrofit configuration
// This service will use the ApiService interface to make requests to the defined base URL
val recipeService = _retrofit.create(ApiService::class.java)

private val _restCountriesRetrofit = Retrofit.Builder().baseUrl("https://restcountries.com/v3.1/").addConverterFactory(GsonConverterFactory.create()).build()

val restCountryRecipeService = _restCountriesRetrofit.create(ApiService::class.java)

var foodCategoryChosen = { mutableStateOf("") }




interface ApiService {
    // The @GET annotation indicates that this function will perform an HTTP GET request.
    // It is used to request data from a specific endpoint, in this case, "categories.php".
    @GET("categories.php")

    // The 'suspend' keyword is used to indicate that this function can be called asynchronously.
    // This means that it can pause its execution until the data is received, without blocking the user interface.
    suspend fun getCategories(): CategoriesResponse

    @GET("list.php?a=list")
    suspend fun getCountries(): CountryResponse

    @GET("all")
    suspend fun getRestCountries(): List<RestCountry>

    @GET("random.php")
    suspend fun getRandomMeal(): MealDetailResponse

    @GET("filter.php?")
    suspend fun getMealsByCategorySelected( @Query("c") categoryChosen: String): MealsResponse

    @GET("lookup.php?")
    suspend fun getMealDetailFromId(@Query("i") mealId: String): MealDetailResponse
}

