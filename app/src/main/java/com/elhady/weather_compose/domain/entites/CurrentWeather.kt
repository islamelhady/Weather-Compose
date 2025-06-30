package com.elhady.weather_compose.domain.entites

data class CurrentWeather (
    val time: String,
    val temperature: Double,
    val humidity: Int,
    val uvIndex: Double,
    val isDay: Boolean,
    val rain: Double,
    val weatherCode: Int,
    val pressure: Double,
    val windSpeed: Double
)