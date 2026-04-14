package com.elhady.weather_compose.domain.entites

data class HourlyUnits(
    val time: String,
    val temperature: String,
    val weatherCode: String
)