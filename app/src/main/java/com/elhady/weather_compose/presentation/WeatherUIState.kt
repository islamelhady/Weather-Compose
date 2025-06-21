package com.elhady.weather_compose.presentation

import com.elhady.weather_compose.domain.entities.Weather

data class WeatherUIState(
    val weather: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)