package com.example.recipe_app.utils

import com.example.recipe_app.R


fun getDrawableResourceId(strArea: String): Int {
    return when (strArea) {
        "American" -> R.drawable.american
        "British" -> R.drawable.british
        "Canadian" -> R.drawable.canadian
        "Chinese" -> R.drawable.chinese
        "Croatian" -> R.drawable.croatian
        // Adicione mais mapeamentos conforme suas imagens
        else -> R.drawable.dutch // Imagem padrão ou placeholder
    }

}