package com.example.recipe_app.app_ui.view


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.navigation.NavController
import com.example.recipe_app.models.Category
import com.example.recipe_app.models.Country

import com.example.recipe_app.app_ui.viewModel.drinks.AlcoholicDrinksViewModel
import com.example.recipe_app.app_ui.viewModel.meals.CategoryViewModel
import com.example.recipe_app.app_ui.viewModel.CountryViewModel
import com.example.recipe_app.app_ui.components.AlcoholicDrinksRow
import com.example.recipe_app.app_ui.components.FilterSection
import com.example.recipe_app.app_ui.components.MealsByCategoryRow
import com.example.recipe_app.app_ui.components.MixNMealByArea
import com.example.recipe_app.models.Drinks


@Composable
fun MixNMealView(navController: NavController, modifier: Modifier = Modifier) {

    //TODO: ALGUMAS FUNCÕES SAO DUPLICACAO DE CODIGO, PARA EVITAR
    //TODO: APRIMAR OS BOTOES PARA USAR O NAVIGATE
    //TODO: ATUALIZAR A DOCUMENTAÇÃO


    // Retrieve the MainViewModel instance to manage UI-related data
    val countryViewModel: CountryViewModel = viewModel()

    val recipeViewModel: CategoryViewModel = viewModel()

    val alcoholicDrinksViewModel : AlcoholicDrinksViewModel = viewModel()

    // Observe the state from the ViewModel to update the UI reactively
    val viewState by countryViewModel.countriesState

    val viewAlcoholicDrinksState by alcoholicDrinksViewModel.alcoholicDrinksState

    val viewCategoryState by recipeViewModel.categoriesState

    //TODO: VERIFICAR O MODO LIGHT/DARK DO TELEFONE

    Box(modifier = Modifier.fillMaxWidth()) {
        when {
            // If the data is currently loading, show a circular progress indicator in the center
            viewState.loading -> {

                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text("ERROR OCCURRED ${viewState.error}")
            }

            // If loading is complete and there is no error, display the categories
            else -> {
                MixNMealComposable(viewState.list, viewCategoryState.list, viewAlcoholicDrinksState.list, navController)
            }
        }
    }
}

@Composable
fun MixNMealComposable(
    countries: List<Country>,
    viewCategoryState: List<Category>,
    viewAlcoholicDrinksState: List<Drinks>,
    navController: NavController,

    ) {
    val isDarkTheme = isSystemInDarkTheme()

    // Wrapping the content in a LazyColumn to make it scrollable
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            //.padding(8.dp)

    ) {
        item {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "MixNMeal Discoveries ",
                color = if (isDarkTheme) Color.White else Color.DarkGray,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                ),
                modifier = Modifier.padding(top = 16.dp, start = 16.dp)
            )
        }
        item {
            FilterSection(isDarkTheme, navController)
        }

        item {
            MixNMealByArea(isDarkTheme, countries)
        }
        item {
            MealsByCategoryRow(isDarkTheme, viewCategoryState)
        }

        item{
            AlcoholicDrinksRow(isDarkTheme,viewAlcoholicDrinksState.asReversed())
        }

    }
}











