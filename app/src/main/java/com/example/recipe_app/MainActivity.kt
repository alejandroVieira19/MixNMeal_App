package com.example.recipe_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipe_app.app_ui.navigation.MixNMealNavigator
import com.example.recipe_app.ui.theme.Recipe_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Recipe_AppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MixNMealApp()
                }
            }
        }
    }
}


@Composable
fun MixNMealApp() {
    MixNMealNavigator()
}
