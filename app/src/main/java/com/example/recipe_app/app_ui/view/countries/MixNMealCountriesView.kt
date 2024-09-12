package com.example.recipe_app.app_ui.view.countries

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.recipe_app.app_ui.components.MealByAreaCard
import com.example.recipe_app.app_ui.components.MixNMealByArea
import com.example.recipe_app.app_ui.view.meal.MealyByCategoryItem
import com.example.recipe_app.app_ui.view.meal.RandomMealScreen
import com.example.recipe_app.app_ui.viewModel.CountryViewModel
import com.example.recipe_app.models.Country
import com.example.recipe_app.utils.getDrawableResourceId

@Composable
fun MixNMealCountriesView(navController: NavController, modifier: Modifier = Modifier){

    val countryViewModel: CountryViewModel = viewModel()

    val viewState by countryViewModel.countriesState

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
                MixNMealCountriesScreen(viewState.list, navController)
            }
        }
    }

}

@Composable
fun MixNMealCountriesScreen(countries: List<Country>, navController: NavController) {

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
                text = "MixNMeal Areas",
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

        LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
            items(countries) { country ->
                MixNMealCountriesCard(darkTheme,country,navController)
            }
        }
    }
}

@Composable
fun MixNMealCountriesCard(darkTheme: Boolean, country: Country, navController: NavController) {
    val imageId = getDrawableResourceId(country.strArea)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = {navController.navigate("meals_by_country/${country.strArea}")}),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = if (darkTheme) Color.DarkGray else Color.LightGray) // Cor de fundo do cartão
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image Placeholder
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = null, // Descrição acessível opcional
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = country.strArea,
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = if (darkTheme) Color.White else Color.Black
                    )
                )
            }
        }
    }
}
