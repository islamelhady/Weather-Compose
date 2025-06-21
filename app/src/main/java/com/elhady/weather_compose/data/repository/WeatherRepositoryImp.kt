package com.elhady.weather_compose.data.repository

import com.elhady.weather_compose.data.mapper.toWeather
import com.elhady.weather_compose.data.remote.WeatherApiService
import com.elhady.weather_compose.domain.entities.Weather
import com.elhady.weather_compose.domain.repository.WeatherRepository

class WeatherRepositoryImp(private val weatherApiService: WeatherApiService) : WeatherRepository {

    override suspend fun getWeather(latitude: Double, longitude: Double): Result<Weather> {
        return try {
            val weatherDto = weatherApiService.getWeather(latitude, longitude)
            val weather = weatherDto.toWeather()
            Result.success(weather)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}