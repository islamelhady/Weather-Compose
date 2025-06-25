package com.elhady.weather_compose.data.remote

import com.elhady.weather_compose.data.dto.WeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherApiServiceImpl(private val client: HttpClient) : WeatherApiService {

    override suspend fun getWeather(latitude: Double, longitude: Double): WeatherDto {
        return client.get(BASE_URL) {
            parameter("latitude", latitude)
            parameter("longitude", longitude)
            parameter("current", CURRENT_WEATHER_PARAMS)
        }.body()
    }

    companion object {
        private const val BASE_URL = "https://api.open-meteo.com/v1/forecast"
        private const val CURRENT_WEATHER_PARAMS = "temperature_2m,weather_code,wind_speed_10m"
    }
}