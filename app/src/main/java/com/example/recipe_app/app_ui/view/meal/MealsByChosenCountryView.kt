package com.example.recipe_app.app_ui.view.meal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.recipe_app.app_ui.viewModel.meals.MealsByChosenCountryViewModel

@Composable
fun MealsByChosenCountryView(navController: NavController, chosenCountry:String, modifier: Modifier = Modifier) {

    val mealsByChosenCountryViewModel: MealsByChosenCountryViewModel = viewModel()

    // Atualiza a categoria e carrega os dados
    LaunchedEffect(chosenCountry) {
        mealsByChosenCountryViewModel.selectChosenCountry(chosenCountry)
    }


    // Observe the state from the ViewModel to update the UI reactively
    val viewState by mealsByChosenCountryViewModel.allMealsByChosenCountryState

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            // If the data is currently loading, show a circular progress indicator in the center
            viewState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error != null -> {
                Text("ERROR OCCURRED")
            }
            // If loading is complete and there is no error, display the categories
            else -> {
                MealsByCategoryChosenScreen(viewState.list, navController, chosenCountry)
            }
        }
    }
}




