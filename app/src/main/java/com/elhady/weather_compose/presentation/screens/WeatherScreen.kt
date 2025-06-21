package com.elhady.weather_compose.presentation.screens

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.elhady.weather_compose.presentation.WeatherUIState
import com.elhady.weather_compose.presentation.WeatherViewModel
import com.elhady.weather_compose.presentation.components.CurrentWeatherHeader
import com.elhady.weather_compose.presentation.components.HourlyForecastSection
import com.elhady.weather_compose.presentation.components.WeatherDetailsGrid
import com.elhady.weather_compose.ui.theme.LightBackground
import com.elhady.weather_compose.ui.theme.LightBlue
import org.koin.androidx.compose.koinViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = koinViewModel()
) {
    val locationPermissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    LaunchedEffect(locationPermissionsState.allPermissionsGranted) {
        if (!locationPermissionsState.allPermissionsGranted) {
            locationPermissionsState.launchMultiplePermissionRequest()
        } else {
            viewModel.loadWeather()
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.error != null -> {
                Text(
                    text = state.error!!,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            !locationPermissionsState.allPermissionsGranted -> {
                PermissionDeniedContent(modifier = Modifier.align(Alignment.Center))
            }
            else -> {
                WeatherContent(state = state)
            }
        }
    }
}

@Composable
fun WeatherContent(state: WeatherUIState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightBlue,
                        LightBackground
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            CurrentWeatherHeader(state = state)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                CurrentWeatherHeader(state = state)

                Spacer(modifier = Modifier.height(32.dp))

            }
            WeatherDetailsGrid(state = state)
            Spacer(modifier = Modifier.height(32.dp))
            HourlyForecastSection(state = state)
        }
    }
}

@Composable
fun PermissionDeniedContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Location permission is required to show the weather.", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(height = 16.dp))
        Button(onClick = { /* TODO: Open app settings */ }) {
            Text("Grant Permission")
        }
    }
}