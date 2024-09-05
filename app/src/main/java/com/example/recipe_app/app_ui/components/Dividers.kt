package com.example.recipe_app.app_ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
 fun DividerComposable(isDarkTheme: Boolean) {
    Divider(
        color = if(isDarkTheme) Color.White else Color.Gray,
        thickness = 2.dp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}