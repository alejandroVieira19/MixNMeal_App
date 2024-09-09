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

@Composable
fun FilterSection(isDarkTheme: Boolean, navController: NavController) {

    DividerComposable(isDarkTheme)

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento entre os itens
    ) {
        items(listOf("Food Categories", "Random Drink", "Area", "Non Alcoholic")) { filter ->
            FilterButton(filter, isDarkTheme) {
                when (filter) {
                    "Food Categories" -> navController.navigate("food_categories")
                    "Random Drink" -> navController.navigate("random_drink")
                    "Area" -> navController.navigate("area")
                    "Non Alcoholic" -> navController.navigate("non_alcoholic")
                }
            }
        }
    }
}



