package com.example.weatherapp.ui.theme.ui

import WeatherViewModel
import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.location.LocationHelper

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {

    var city by remember { mutableStateOf("") }

    val weather by viewModel.weather.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    val context = LocalContext.current
    val locationHelper = remember { LocationHelper(context) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->

            val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true

            if (granted) {
                locationHelper.getLocation { lat, lon ->
                    println("LAT: $lat LON: $lon")
                    viewModel.getWeatherByLocation(lat, lon)
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF1565C0), Color(0xFF1E88E5))
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "📍 ${weather?.name ?: "Fetching..."}",
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = city,
                onValueChange = { city = it },
                placeholder = { Text("Search city") },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { viewModel.getWeather(city) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search")
            }

            Spacer(modifier = Modifier.height(30.dp))

            if (isLoading) {
                CircularProgressIndicator(color = Color.White)
            }

            error?.let {
                Text(it, color = Color.Red)
            }

            weather?.let {

                Spacer(modifier = Modifier.height(20.dp))

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
    }
}