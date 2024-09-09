package com.example.recipe_app.app_ui.View.drinks


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import coil.compose.rememberAsyncImagePainter
import com.example.recipe_app.app_ui.View.meal.MealCategoryScreen
import com.example.recipe_app.app_ui.ViewModel.drinks.RandomDrinkViewModel
import com.example.recipe_app.app_ui.ViewModel.meals.CategoryViewModel
import com.example.recipe_app.app_ui.components.DividerComposable
import com.example.recipe_app.models.Category
import com.example.recipe_app.models.RandomDrink

@Composable
fun RandomDrinkView(navController: NavController, modifier: Modifier = Modifier) {
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
                    imageVector = Icons.Default.ArrowBack,
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
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Divider(
            color = if (darkTheme) Color.Gray else Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        randomDrinkScreen(viewState.list)
    }
}

@Composable
fun randomDrinkScreen(drinks: List<RandomDrink>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        items(drinks) { drink ->
            DrinkCard(drink)
        }
    }
}

@Composable
fun DrinkCard(drink: RandomDrink) {
    val darkTheme = isSystemInDarkTheme()


    Column(
            modifier = Modifier.fillMaxSize()
        ) {
        // Nome da bebida
            Text(
                text = drink.strDrink,
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp,
                    color = if (darkTheme) Color.White else Color.Black
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            DividerComposable(darkTheme)
            Spacer(modifier = Modifier.height(8.dp))

            // Imagem da bebida
            AsyncImage(
                model = drink.strDrinkThumb,
                contentDescription = drink.strDrink,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Categoria, Alcoólico, Copo
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


            Spacer(modifier = Modifier.height(24.dp))

            // Ingredientes
            Text(
                text = "Drink Ingredients:",
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp,
                    color = if (darkTheme) Color.White else Color.Black
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Lista de Ingredientes
            Column(

            ) {
                drink.strIngredient1?.let {
                    Text(
                        text = "- ${drink.strMeasure1} $it",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 20.sp,
                        color = if (darkTheme) Color.White else Color.Black
                    )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                drink.strIngredient2?.let {
                    Text(
                        text = "- ${drink.strMeasure2} $it",
                            style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 20.sp,
                        color = if (darkTheme) Color.White else Color.Black
                    )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                drink.strIngredient3?.let {
                    Text(text = "- ${drink.strMeasure3} $it",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            color = if (darkTheme) Color.White else Color.Black
                        )
                        )
                }
                Spacer(modifier = Modifier.height(8.dp))
                drink.strIngredient4?.let {
                    Text(text = "- ${drink.strMeasure4} $it",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            color = if (darkTheme) Color.White else Color.Black
                        ))
                }
                Spacer(modifier = Modifier.height(8.dp))
                drink.strIngredient5?.let {
                    Text(text = "- ${drink.strMeasure5} $it",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            color = if (darkTheme) Color.White else Color.Black
                        ))
                }
                Spacer(modifier = Modifier.height(8.dp))
                drink.strIngredient6?.let {
                    Text(text = "- ${drink.strMeasure6} $it",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            color = if (darkTheme) Color.White else Color.Black
                        ))
                }
                Spacer(modifier = Modifier.height(8.dp))
                drink.strIngredient7?.let {
                    Text(text = "- ${drink.strMeasure7} $it",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            color = if (darkTheme) Color.White else Color.Black
                        ))
                }
                Spacer(modifier = Modifier.height(8.dp))
                drink.strIngredient8?.let {
                    Text(text = "- ${drink.strIngredient8} $it",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            color = if (darkTheme) Color.White else Color.Black
                        ))
                }
                Spacer(modifier = Modifier.height(8.dp))
                drink.strIngredient9?.let {
                    Text(text = "- ${drink.strIngredient9} $it",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            color = if (darkTheme) Color.White else Color.Black
                        ))
                }
                Spacer(modifier = Modifier.height(8.dp))
                drink.strIngredient10?.let {
                    Text(text = "- ${drink.strIngredient10} $it",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            color = if (darkTheme) Color.White else Color.Black
                        ))
                }
                Spacer(modifier = Modifier.height(8.dp))
                drink.strIngredient11?.let {
                    Text(text = "- ${drink.strIngredient11} $it",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            color = if (darkTheme) Color.White else Color.Black
                        ))
                }
                Spacer(modifier = Modifier.height(8.dp))
                drink.strIngredient12?.let {
                    Text(text = "- ${drink.strIngredient12} $it",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            color = if (darkTheme) Color.White else Color.Black
                        ))
                }
                Spacer(modifier = Modifier.height(8.dp))
                drink.strIngredient13?.let {
                    Text(text = "- ${drink.strIngredient13} $it",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            color = if (darkTheme) Color.White else Color.Black
                        ))
                }
            }
        }
}



