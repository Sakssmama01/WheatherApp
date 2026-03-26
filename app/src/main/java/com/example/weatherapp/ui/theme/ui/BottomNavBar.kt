package com.example.weatherapp.ui.theme.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomNavBar(navController: NavController) {

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("home") },
            icon = { Text("🏠") },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("search") },
            icon = { Text("🔍") },
            label = { Text("Search") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("menu") },
            icon = { Text("⚙️") },
            label = { Text("Menu") }
        )
    }
}