package com.example.recipe_app.app_ui.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.recipe_app.app_ui.ViewModel.CategoryViewModel
import com.example.recipe_app.models.Category

@Composable
fun MealsByCategoryView(modifier: Modifier = Modifier) {

    // Retrieve the MainViewModel instance to manage UI-related data
    val recipeViewModel: CategoryViewModel = viewModel()

    // Observe the state from the ViewModel to update the UI reactively
    val viewState by recipeViewModel.categoriesState

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
                CategoryScreen(categories = viewState.list)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>) {
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories) {
                category ->  CategoryItem(category = category)
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth().wrapContentHeight(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray) // Cor de fundo do cartão
        ){
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(category.strCategoryThumb),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(8.dp)) // Espaço entre a imagem e o texto

                // Linha de separação
                Divider(
                    color = Color.Gray,
                    thickness = 2.dp, // Espessura da linha
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text(
                    text = category.strCategory,
                    color = Color.Black,
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

