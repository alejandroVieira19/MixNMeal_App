package com.example.recipe_app.app_ui.view.drinks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.recipe_app.app_ui.viewModel.drinks.AlcoholicDrinksViewModel

@Composable
fun AlcoholicDrinksView(navController: NavController) {

    val alcoholicDrinksViewModel : AlcoholicDrinksViewModel = viewModel()

    val viewAlcoholicDrinksState by alcoholicDrinksViewModel.alcoholicDrinksState

    NonAlcoholicDrinksScreen(viewAlcoholicDrinksState.list, navController, "alcoholic")
}

