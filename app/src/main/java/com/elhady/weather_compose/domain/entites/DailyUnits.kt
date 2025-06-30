package com.elhady.weather_compose.domain.entites

data class DailyUnits(
    val time: String,
    val mixTemperature: String,
    val minTemperature: String,
    val weatherCode: String
)