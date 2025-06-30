package com.elhady.weather_compose.domain.repository

import com.elhady.weather_compose.domain.entites.Location
import com.elhady.weather_compose.domain.entites.Weather

interface WeatherRepository {
    suspend fun getWeather(location: Location): Weather
}