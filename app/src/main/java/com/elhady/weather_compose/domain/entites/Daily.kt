package com.elhady.weather_compose.domain.entites

data class Daily(
    val time: String,
    val maxTemperature: Double,
    val minTemperature: Double,
    val weatherCode: Int
)
