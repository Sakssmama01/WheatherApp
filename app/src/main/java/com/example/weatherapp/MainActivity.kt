package com.example.weatherapp

import WeatherViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.weatherapp.ui.theme.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherApp()
        }
    }
}

@Composable
fun WeatherApp() {

    val navController = rememberNavController()
    val viewModel: WeatherViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(viewModel, navController)
        }

        composable("search") {
            SearchScreen(viewModel, navController)
        }
    }
}