package com.example.recipe_app.app_ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipe_app.app_ui.View.MealsByCategoryView
import com.example.recipe_app.app_ui.View.MixNMealView

@Composable
fun FilterSection(isDarkTheme: Boolean, navController: NavController) {

    DividerComposable(isDarkTheme)

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento entre os itens
    ) {
        items(listOf("Food Categories", "Drink Categories", "Area", "Non Alcoholic")) { filter ->
            FilterButton(filter, isDarkTheme) {
                when (filter) {
                    "Food Categories" -> navController.navigate("food_categories")
                    "Drink Categories" -> navController.navigate("drink_categories")
                    "Area" -> navController.navigate("area")
                    "Non Alcoholic" -> navController.navigate("non_alcoholic")
                }
            }
        }
    }
}



