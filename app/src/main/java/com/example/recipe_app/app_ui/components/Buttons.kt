package com.example.recipe_app.app_ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp



@Composable
fun FilterButton(text: String, isDarkTheme: Boolean) {

    Button(onClick = { },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor =  if(isDarkTheme) Color.DarkGray else Color.LightGray)) {
        Text(text = text,
            color = if(isDarkTheme) Color.White else Color.DarkGray,
            style = TextStyle(fontWeight = FontWeight.Bold), fontStyle = FontStyle.Italic)
    }
}