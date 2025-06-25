package com.elhady.weather_compose.domain.repository

import com.elhady.weather_compose.domain.entities.Weather

interface WeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): Result<Weather>
}