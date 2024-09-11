package com.example.recipe_app.app_ui.view.meal

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.recipe_app.app_ui.viewModel.meals.MealDetailsViewModel
import com.example.recipe_app.models.MealDetails

@Composable
fun MealDetailView(navController: NavHostController, mealId: String, modifier: Modifier = Modifier) {

    // Retrieve the MainViewModel instance to manage UI-related data
    val mealDetailsViewModel: MealDetailsViewModel = viewModel()

    // Atualiza a categoria e carrega os dados
    LaunchedEffect(mealId) {
        mealDetailsViewModel.selectedMeal(mealId)
    }
    // Observe the state from the ViewModel to update the UI reactively
    val viewState by mealDetailsViewModel.mealDetailState

    // Create a Box container that takes up the full screen size
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
                MealDetailsScreen(navController, viewState.list)
            }
        }
    }
}

@Composable
fun MealDetailsScreen(navController: NavHostController, mealDetail: List<MealDetails>) {
    val darkTheme = isSystemInDarkTheme()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow Back",
                    tint = if (darkTheme) Color.White else Color(0xFF6200EE)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "MixNMeal Meal",
                color = if (darkTheme) Color.White else Color.DarkGray,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                ),
            )
        }

        HorizontalDivider(
            color = if (darkTheme) Color.Gray else Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        RandomMealScreen(mealDetail)
    }
}

