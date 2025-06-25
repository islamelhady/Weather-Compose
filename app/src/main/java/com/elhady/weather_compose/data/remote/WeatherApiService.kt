package com.elhady.weather_compose.data.remote

import com.elhady.weather_compose.data.dto.WeatherDto

interface WeatherApiService {
    suspend fun getWeather(latitude: Double, longitude: Double): WeatherDto
}