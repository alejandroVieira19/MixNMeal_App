package com.example.recipe_app.utils

import com.example.recipe_app.R

fun getDrawableResourceId(strArea: String): Int {
    return when (strArea) {
        "American" -> R.drawable.american
        "British" -> R.drawable.british
        "Canadian" -> R.drawable.canadian
        "Chinese" -> R.drawable.chinese
        "Croatian" -> R.drawable.croatian
        "Dutch" -> R.drawable.dutch
        "Egyptian" -> R.drawable.egypt
        "French" -> R.drawable.france
        "Greek" -> R.drawable.greece
        "Indian" -> R.drawable.india
        "Irish" -> R.drawable.ireland
        "Italian" -> R.drawable.italy
        "Jamaican" -> R.drawable.jamaica
        "Japanese" -> R.drawable.japan
        "Kenyan" -> R.drawable.kenya
        "Malaysian" -> R.drawable.malaysia
        "Mexican" -> R.drawable.mexico
        "Moroccan" -> R.drawable.morocco
        "Filipino" -> R.drawable.philippines
        "Polish" -> R.drawable.poland
        "Portuguese" -> R.drawable.portugal
        "Russian" -> R.drawable.russia
        "Spanish" -> R.drawable.spain
        "Thai" -> R.drawable.thailand
        "Tunisian" -> R.drawable.tunisia
        "Turkish" -> R.drawable.turkey
        "Ukrainian" -> R.drawable.ukraine
        "Vietnamese" -> R.drawable.vietnam
        else -> R.drawable.rest // Imagem padrão ou placeholder
    }
}

