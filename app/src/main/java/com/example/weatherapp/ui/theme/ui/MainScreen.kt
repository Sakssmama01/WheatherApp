package com.example.weatherapp.ui.theme.ui

import WeatherViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    viewModel: WeatherViewModel,
    navController: NavHostController
) {

    val weather by viewModel.weather.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF4FACFE), Color(0xFF00F2FE))
                )
            )
            .padding(20.dp)
    ) {

        Column {

            Spacer(modifier = Modifier.height(20.dp))

            // 🔍 SEARCH BAR STYLE BUTTON
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("search") },
                shape = RoundedCornerShape(50.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Text(
                    "🔍 Search for a city",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // 📍 LOCATION
            Text(
                text = "📍 ${weather?.name ?: "Fetching..."}",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ⏳ LOADING
            if (isLoading) {
                CircularProgressIndicator(color = Color.White)
            }

            // ❌ ERROR
            error?.let {
                Text(it, color = Color.Red)
            }

            // ✅ WEATHER UI
            weather?.let {

                Spacer(modifier = Modifier.height(20.dp))

                // ☁️ ICON + TEMP ROW
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // ☁️ ICON
                    Text(
                        text = "☁️",
                        style = MaterialTheme.typography.displayLarge
                    )

                    Column(horizontalAlignment = Alignment.End) {

                        Text(
                            "${it.main.temp.toInt()}°",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )

                        Text(
                            it.weather.firstOrNull()?.description ?: "",
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // 📦 GLASS CARD
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(25.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(0.2f)
                    )
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {

                        Text(
                            "Weather Details",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            WeatherItem("💧", "Humidity", "${it.main.humidity}%")
                            WeatherItem("🌬️", "Wind", "${it.wind.speed} m/s")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherItem(icon: String, title: String, value: String) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(icon, style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Text(title, color = Color.White)

        Text(value, color = Color.White, fontWeight = FontWeight.Bold)
    }
}