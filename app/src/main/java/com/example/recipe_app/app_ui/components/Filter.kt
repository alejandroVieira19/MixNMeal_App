package com.example.recipe_app.app_ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterSection(isDarkTheme: Boolean) {

    DividerComposable(isDarkTheme)


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento entre os itens
    ) {
        items(listOf("Food Categories", "Drink Categories", "Area", "Non Alcoholic")) { filter ->
            FilterButton(filter, isDarkTheme)
        }
    }
}