package com.example.recipe_app.app_ui.view.meal

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipe_app.app_ui.components.DividerComposable
import com.example.recipe_app.app_ui.viewModel.meals.RandomMealViewModel
import com.example.recipe_app.models.MealDetails

@Composable
fun RandomMealView(navController: NavController) {
    val darkTheme = isSystemInDarkTheme()

    val randomMealViewModel: RandomMealViewModel = viewModel()

    // Observe the state from the ViewModel to update the UI reactively
    val viewState by randomMealViewModel.randomMealState

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
                text = "Random Meal of the day",
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

        RandomMealScreen(viewState.list)
    }
}

@Composable
fun RandomMealScreen(mealDetails: List<MealDetails>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(mealDetails) { meal ->
            MealScreen(meal)
        }
    }

}

@Composable
private fun MealScreen(meal: MealDetails) {
    val darkTheme = isSystemInDarkTheme()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        RandomMealHeadText(darkTheme, meal.strMeal)
        RandomMealImage(darkTheme, meal.strMealThumb)
        RandomMealDetails(darkTheme, meal)
        RandomMealIngredients(darkTheme, meal)
    }
}

@Composable
fun RandomMealHeadText(darkTheme: Boolean, mealName: String) {
    Text(
        text = mealName,
        style = TextStyle(
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            color = if (darkTheme) Color.White else Color.Black
        ),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun RandomMealImage(darkTheme: Boolean, mealImage: String) {
    DividerComposable(darkTheme)
    Spacer(modifier = Modifier.height(8.dp))


    AsyncImage(
        model = mealImage,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
fun RandomMealDetails(darkTheme: Boolean, meal: MealDetails) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Category: ${meal.strCategory}",
        style = TextStyle(
            color = if (darkTheme) Color.LightGray else Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
    )
    if (!meal.strDrinkAlternate.isNullOrEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Drink Alternate: ${meal.strDrinkAlternate}",
            style = TextStyle(
                color = if (darkTheme) Color.LightGray else Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    if (!meal.strArea.isNullOrEmpty()){
        Text(
            text = "Area: ${meal.strArea}",
            style = TextStyle(
                color = if (darkTheme) Color.LightGray else Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp)
        )
    }
}

@Composable
fun RandomMealIngredients(darkTheme: Boolean, meal: MealDetails) {
    Spacer(modifier = Modifier.height(24.dp))

    Text(
        text = "Meal Ingredients:",
        style = TextStyle(
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            color = if (darkTheme) Color.White else Color.Black
        )
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Lista de ingredientes e medidas
    val ingredients = listOf(
        meal.strIngredient1 to meal.strMeasure1,
        meal.strIngredient2 to meal.strMeasure2,
        meal.strIngredient3 to meal.strMeasure3,
        meal.strIngredient4 to meal.strMeasure4,
        meal.strIngredient5 to meal.strMeasure5,
        meal.strIngredient6 to meal.strMeasure6,
        meal.strIngredient7 to meal.strMeasure7,
        meal.strIngredient8 to meal.strMeasure8,
        meal.strIngredient9 to meal.strMeasure9,
        meal.strIngredient10 to meal.strMeasure10,
        meal.strIngredient11 to meal.strMeasure11,
        meal.strIngredient12 to meal.strMeasure12,
        meal.strIngredient13 to meal.strMeasure13,
        meal.strIngredient14 to meal.strMeasure14,
        meal.strIngredient15 to meal.strMeasure15,
        meal.strIngredient16 to meal.strMeasure16,
        meal.strIngredient17 to meal.strMeasure17,
        meal.strIngredient18 to meal.strMeasure18,
        meal.strIngredient19 to meal.strMeasure19,
        meal.strIngredient20 to meal.strMeasure20,

        )

    Column {
        for ((ingredient, measure) in ingredients) {
            if (!ingredient.isNullOrEmpty()) {
                Text(
                    text = "- ${measure ?: ""} $ingredient",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 20.sp,
                        color = if (darkTheme) Color.White else Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}