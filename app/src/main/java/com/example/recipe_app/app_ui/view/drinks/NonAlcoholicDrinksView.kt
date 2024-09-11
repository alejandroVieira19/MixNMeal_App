package com.example.recipe_app.app_ui.view.drinks

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

import com.example.recipe_app.app_ui.components.DividerComposable
import com.example.recipe_app.app_ui.viewModel.drinks.NonAlcoholicDrinksViewModel

import com.example.recipe_app.models.Drinks

@Composable
fun NonAlcoholicDrinksView(navController: NavController,modifier: Modifier = Modifier) {


    val nonAlcoholicsDrinksViewModel: NonAlcoholicDrinksViewModel = viewModel()

    val viewState by nonAlcoholicsDrinksViewModel.nonAlcoholicDrinksState


    Box(modifier = Modifier.fillMaxSize()) {
        when {

            viewState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error != null -> {
                Text("ERROR OCCURRED")
            }

            else -> {
                NonAlcoholicDrinksScreen(viewState.list, navController, "non")
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun NonAlcoholicDrinksScreen(
    nonAlcoholicDrinks: List<Drinks>,
    navController: NavController,
    typeOfDrink: String
) {

    val darkTheme = isSystemInDarkTheme()

    var nameDrinkText = mutableStateOf("Non Alcoholic Drinks")

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(32.dp))
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically, // Isso alinha verticalmente os itens no Row
            modifier = Modifier.padding(start = 16.dp) // Padding geral para o Row
        ) {
            IconButton(
                onClick = {navController.popBackStack()},
                modifier = Modifier.size(24.dp) // Mantém o tamanho do ícone
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow Back",
                    tint = if (darkTheme) Color.White else Color(0xFF6200EE) // Usa roxo se não for dark mode
                )
            }

            Spacer(modifier = Modifier.width(8.dp)) // Espaço entre o ícone e o texto

            if(typeOfDrink.equals("alcoholic")) {
                nameDrinkText.value = "Alcoholic Drinks"
            }

            Text(
                text = "MixNMeal ${nameDrinkText.value}",
                color = if (darkTheme) Color.White else Color.DarkGray,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                ),
                modifier = Modifier.align(Alignment.CenterVertically) // Isso garante que o texto esteja centralizado verticalmente
            )
        }
        DividerComposable(darkTheme)
        LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
            items(nonAlcoholicDrinks) { drinks ->
                NonAlcoholicDrinksItem(drinks, darkTheme, navController)

            }
        }
    }

}

@Composable
fun NonAlcoholicDrinksItem(drinks: Drinks, darkTheme: Boolean, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = {navController.navigate("drink_detail/${drinks.idDrink}")}),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = if(darkTheme) Color.DarkGray else Color.LightGray) // Cor de fundo do cartão
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(drinks.strDrinkThumb),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(8.dp)) // Espaço entre a imagem e o texto

                // Linha de separação
                HorizontalDivider(
                    color = Color.Gray,
                    thickness = 2.dp, // Espessura da linha
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text(
                    text = drinks.strDrink,
                    color = if(darkTheme) Color.White else Color.LightGray,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

