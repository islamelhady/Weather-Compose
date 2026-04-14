package com.elhady.weather_compose.presentation.state

import com.elhady.weather_compose.domain.entites.Weather


sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val data: Weather) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}