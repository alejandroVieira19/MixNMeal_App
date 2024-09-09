package com.example.recipe_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipe_app.app_ui.View.MealsByCategoryView
import com.example.recipe_app.app_ui.View.MixNMealView
import com.example.recipe_app.ui.theme.Recipe_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Recipe_AppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MixNMealApp()
                }
            }
        }
    }
}


@Composable
fun MixNMealApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mix_n_meal") {
        composable("mix_n_meal") { MixNMealView(navController)  }
        composable("food_categories") { MealsByCategoryView(navController) }
        composable("drink_categories") { /* Composable para Drink Categories */ }
        composable("area") { /* Composable para Area */ }
        composable("non_alcoholic") { /* Composable para Non Alcoholic */ }
    }
}