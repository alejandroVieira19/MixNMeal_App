package com.example.recipe_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


// Configures the Retrofit instance for communication with the internet
private val _retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build()

// Creates a service using the Retrofit configuration
// This service will use the ApiService interface to make requests to the defined base URL
val recipeService = _retrofit.create(ApiService::class.java)



interface ApiService {
    // The @GET annotation indicates that this function will perform an HTTP GET request.
    // It is used to request data from a specific endpoint, in this case, "categories.php".
    @GET("categories.php")

    // The 'suspend' keyword is used to indicate that this function can be called asynchronously.
    // This means that it can pause its execution until the data is received, without blocking the user interface.
    suspend fun getCategories(): CategoriesResponse
}

