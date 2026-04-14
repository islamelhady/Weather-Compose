package com.elhady.weather_compose.presentation.state

data class HourlyForecastState (
    val temperature: String,
    val time: String,
    val weatherConditionState: WeatherConditionState,
)