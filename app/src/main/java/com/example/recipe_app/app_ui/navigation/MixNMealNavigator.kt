package com.example.recipe_app.app_ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipe_app.app_ui.view.MixNMealView
import com.example.recipe_app.app_ui.view.drinks.AlcoholicDrinksView
import com.example.recipe_app.app_ui.view.drinks.DrinkDetailView
import com.example.recipe_app.app_ui.view.drinks.NonAlcoholicDrinksView
import com.example.recipe_app.app_ui.view.drinks.RandomDrinkView
import com.example.recipe_app.app_ui.view.meal.MealDetailView
import com.example.recipe_app.app_ui.view.meal.MealsByCategoryChosenView
import com.example.recipe_app.app_ui.view.meal.MealsByCategoryView
import com.example.recipe_app.app_ui.view.meal.MealsByChosenCountryView
import com.example.recipe_app.app_ui.view.meal.RandomMealView

@Composable
fun MixNMealNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mix_n_meal") {
        composable("mix_n_meal") { MixNMealView(navController)  }
        composable("food_categories") { MealsByCategoryView(navController) }
        composable("random_drink") { RandomDrinkView(navController) }
        composable("random_meal") { RandomMealView(navController) }
        composable("non_alcoholic") { NonAlcoholicDrinksView(navController) }
        composable("alcoholic_drinks") { AlcoholicDrinksView(navController) }

        composable("all_meals_by_category_chosen/{category}"){
                backStackEntry -> val categoryChosen = backStackEntry.arguments?.getString("category") ?: ""
            MealsByCategoryChosenView(navController, categoryChosen)
        }

        composable("meal_detail/{id}"){
                backStackEntry -> val mealId = backStackEntry.arguments?.getString("id") ?: ""
            MealDetailView(navController, mealId)
        }

        composable("drink_detail/{id}"){
                backStackEntry -> val drinkId = backStackEntry.arguments?.getString("id") ?: ""
            DrinkDetailView(navController, drinkId)
        }
        composable("meals_by_country/{country}"){
                backStackEntry -> val country = backStackEntry.arguments?.getString("country") ?: ""
            MealsByChosenCountryView(navController, country)
        }
    }
}