package com.example.recipe_app.app_ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.recipe_app.models.Category

@Composable
fun MealsByCategoryRow(darkTheme: Boolean,
                       category: List<Category>,
                       navController: NavController) {

    Spacer(modifier = Modifier.height(16.dp))

    DividerComposable(darkTheme)

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "  MixNMeal Food Category",
            color = if (darkTheme) Color.White else Color.DarkGray,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = { navController.navigate("food_categories")},
            modifier = Modifier.size(24.dp) // Adjust size to match the aesthetics
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Forward",
                tint = if (darkTheme) Color.White else Color(0xFF6200EE) // Use purple if not in dark mode
            )
        }
    }

    LazyRow{items(category.take(5)) { category ->
        CategoryCard(darkTheme,category,navController)
    }
    }
}

@Composable
fun CategoryCard(darkTheme: Boolean, category: Category, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(width = 180.dp, height = 240.dp)
            .clickable(onClick = {navController.navigate("all_meals_by_category_chosen/${category.strCategory}")}), // Adjust size as needed
        shape = RoundedCornerShape(16.dp),

        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = if (darkTheme) Color.DarkGray else Color.LightGray)
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(category.strCategoryThumb),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f))

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.strCategory,
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = if(darkTheme) Color.White else Color.Black
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Touch to see more",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                    color = if(darkTheme) Color.White else Color.Black
                )
            )
        }
    }
}

