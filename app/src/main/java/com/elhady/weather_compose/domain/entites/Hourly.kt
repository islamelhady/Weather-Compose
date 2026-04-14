package com.elhady.weather_compose.domain.entites

data class Hourly(
    val time: String,
    val temperature: Double,
    val weatherCode: Int
)