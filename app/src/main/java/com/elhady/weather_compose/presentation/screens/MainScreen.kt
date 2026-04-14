package com.elhady.weather_compose.presentation.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CloudOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elhady.weather_compose.presentation.viewmodel.WeatherViewModel
import com.elhady.weather_compose.presentation.composable.sections.CurrentWeatherDetails
import com.elhady.weather_compose.presentation.composable.sections.ForecastForToday
import com.elhady.weather_compose.presentation.composable.sections.ForecastForWeek
import com.elhady.weather_compose.presentation.composable.sections.WeatherSummary
import com.elhady.weather_compose.presentation.state.WeatherState
import com.elhady.weather_compose.presentation.state.WeatherUiState
import com.elhady.weather_compose.presentation.state.getFakeWeatherState
import com.elhady.weather_compose.presentation.state.toWeatherState
import com.elhady.weather_compose.presentation.theme.MyWeatherTheme
import com.elhady.weather_compose.presentation.theme.WeatherTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    viewModel: WeatherViewModel,
    modifier: Modifier = Modifier
) {

    val weatherUiState by viewModel.weatherUiState.collectAsState()

    Crossfade(targetState = weatherUiState, label = "WeatherUiStateFade") { uiState ->
        when (uiState) {
            is WeatherUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }

            is WeatherUiState.Error -> {
                ErrorScreen(
                    message = uiState.message,
                    onRetry = { viewModel.fetchWeatherData() }
                )
            }

            is WeatherUiState.Success -> {
                val weatherData = uiState.data

                WeatherTheme(isDay = weatherData.currentWeather.isDay) {
                    MainContent(
                        weatherState = weatherData.toWeatherState(),
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Composable
private fun ErrorScreen(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.CloudOff,
                contentDescription = null,
                modifier = Modifier.size(120.dp),
                tint = Color(0xFFBDBDBD)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Oops!",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = onRetry,
                modifier = Modifier
                    .height(54.dp)
                    .fillMaxWidth(0.6f),
                shape = RoundedCornerShape(27.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5E49AA)
                )
            ) {
                Text(
                    text = "Try Again",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorPreview(
) {
    ErrorScreen(
        message = "Something went wrong",
        onRetry = {}
    )
}

@Composable
private fun MainContent(
    weatherState: WeatherState,
    modifier: Modifier = Modifier
) {

    Log.i("Weather", weatherState.toString())

    val scrollState = rememberScrollState()
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = modifier
                .background(Brush.linearGradient(MyWeatherTheme.colors.backgrounds))
                .padding(paddingValues)
                .padding(top = 24.dp, bottom = 32.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            WeatherSummary(weatherState.weatherSummaryState, scrollState)
            CurrentWeatherDetails(weatherState.currentWeatherDetailsState)
            ForecastForToday(weatherState.hourlyForecastStates)
            ForecastForWeek(weatherState.dailyForecastState)
        }
    }
}

@Preview(widthDp = 360, heightDp = 760)
@Composable
private fun MainScreenPreview() {

    val weatherState = getFakeWeatherState(isDay = true)

    WeatherTheme(isDay = weatherState.currentWeatherDetailsState.isDay) {
        MainContent(
            weatherState = weatherState,
        )
    }
}