package com.example.weatherapp.ui.theme.ui

import WeatherViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(viewModel: WeatherViewModel) {

    val weather by viewModel.weather.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF3F5AA6), Color(0xFF2C3E73))
                )
            )
            .padding(20.dp)
    ) {

        Column {

            Spacer(modifier = Modifier.height(20.dp))

            // 📍 CITY
            Text(
                text = weather?.name ?: "No data yet",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(30.dp))

            // ⏳ LOADING
            if (isLoading) {
                CircularProgressIndicator(color = Color.White)
            }

            // ❌ ERROR
            error?.let {
                Text(it, color = Color.Red)
            }

            // ✅ WEATHER DATA
            weather?.let {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    // ICON (temporary)
                    Text("☁️", style = MaterialTheme.typography.displayLarge)

                    Column {

                        Text(
                            text = "${it.main.temp.toInt()}°C",
                            style = MaterialTheme.typography.displayLarge,
                            color = Color.White
                        )

                        Text(
                            text = it.weather[0].description,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // 📦 EXTRA INFO CARD
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(0.2f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            "Humidity: ${it.main.humidity}%",
                            color = Color.White
                        )

                        Text(
                            "Wind: ${it.wind.speed} m/s",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}