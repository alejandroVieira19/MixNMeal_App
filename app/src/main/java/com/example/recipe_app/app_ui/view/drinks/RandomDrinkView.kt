package com.example.recipe_app.app_ui.view.drinks



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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

import com.example.recipe_app.app_ui.viewModel.drinks.RandomDrinkViewModel

import com.example.recipe_app.app_ui.components.DividerComposable
import com.example.recipe_app.app_ui.view.meal.ClickableText
import com.example.recipe_app.models.DrinkDetail

@Composable
fun RandomDrinkView(navController: NavController) {
    val darkTheme = isSystemInDarkTheme()

    val randomDrinkViewModel: RandomDrinkViewModel = viewModel()

    // Observe the state from the ViewModel to update the UI reactively
    val viewState by randomDrinkViewModel.randomDrinkState

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
                text = "Random drink of the day",
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

        RandomDrinkScreen(viewState.list)
    }
}


@Composable
fun RandomDrinkScreen(drinks: List<DrinkDetail>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(drinks) { drink ->
            DrinkScreen(drink)
        }
    }
}

@Composable
fun DrinkScreen(drink: DrinkDetail) {
    val darkTheme = isSystemInDarkTheme()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        RandomDrinkHeadText(darkTheme, drink.strDrink)
        RandomDrinkImage(darkTheme, drink.strDrinkThumb)
        RandomDrinkDetails(darkTheme, drink)
        RandomDrinkIngredients(darkTheme, drink)

        if (!drink.strDrinkAlternate.isNullOrEmpty() || !drink.strTags.isNullOrEmpty() || !drink.strVideo.isNullOrEmpty()) {
            DividerComposable(isDarkTheme = darkTheme)
            RandomDrinkMoreInfo(darkTheme, drink)
        }
    }
}

@Composable
fun RandomDrinkMoreInfo(darkTheme: Boolean, drink: DrinkDetail) {
    val context = LocalContext.current

    Text(
        text = "More Information:",
        style = TextStyle(
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            color = if (darkTheme) Color.White else Color.Black
        )
    )

    if (!drink.strDrinkAlternate.isNullOrEmpty()) {
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = "Alternate Drink: ",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = if (darkTheme) Color.White else Color.Black
                )
            )
            ClickableText(drink.strDrinkAlternate, drink.strDrinkAlternate,context,darkTheme)
        }
    }

    if (!drink.strVideo.isNullOrEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = "Video: ",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = if (darkTheme) Color.White else Color.Black
                )
            )
            ClickableText(drink.strVideo, drink.strVideo, context, darkTheme)
        }
    }

    if (!drink.strTags.isNullOrEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = "Tags: ",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = if (darkTheme) Color.White else Color.Black
                )
            )
            ClickableText(drink.strTags, drink.strTags, context, darkTheme)
        }
    }

}


@Composable
fun RandomDrinkHeadText(darkTheme: Boolean, strDrink: String) {
    Text(
        text = strDrink,
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
fun RandomDrinkImage(darkTheme: Boolean, strDrinkThumb: String?) {

    DividerComposable(darkTheme)
    Spacer(modifier = Modifier.height(8.dp))


    AsyncImage(
        model = strDrinkThumb,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}



@Composable
fun RandomDrinkDetails(darkTheme: Boolean, drink: DrinkDetail) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Category: ${drink.strCategory}",
        style = TextStyle(
            color = if (darkTheme) Color.LightGray else Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Alcoholic: ${drink.strAlcoholic}",
        style = TextStyle(
            color = if (darkTheme) Color.LightGray else Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Glass: ${drink.strGlass}",
        style = TextStyle(
            color = if (darkTheme) Color.LightGray else Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)
    )
}


@Composable
fun RandomDrinkIngredients(darkTheme: Boolean, drink: DrinkDetail) {

    Spacer(modifier = Modifier.height(24.dp))


    Text(
        text = "Drink Ingredients:",
        style = TextStyle(
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            color = if (darkTheme) Color.White else Color.Black
        )
    )

    Spacer(modifier = Modifier.height(8.dp))


    val ingredients = listOf(
        drink.strIngredient1 to drink.strMeasure1,
        drink.strIngredient2 to drink.strMeasure2,
        drink.strIngredient3 to drink.strMeasure3,
        drink.strIngredient4 to drink.strMeasure4,
        drink.strIngredient5 to drink.strMeasure5,
        drink.strIngredient6 to drink.strMeasure6,
        drink.strIngredient7 to drink.strMeasure7,
        drink.strIngredient8 to drink.strMeasure8,
        drink.strIngredient9 to drink.strMeasure9,
        drink.strIngredient10 to drink.strMeasure10,
        drink.strIngredient11 to drink.strMeasure11,
        drink.strIngredient12 to drink.strMeasure12,
        drink.strIngredient13 to drink.strMeasure13,
        drink.strIngredient14 to drink.strMeasure14,
        drink.strIngredient15 to drink.strMeasure15)

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


