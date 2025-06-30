package com.elhady.weather_compose.presentation.state

data class DailyForecastState(
    val dayName: String,
    val maxTemperature: Int,
    val minTemperature: Int,
    val weatherConditionState: WeatherConditionState,
)