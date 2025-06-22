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
            parameter("current", API_PARAMS)
        }.body()
    }

    companion object {
        private const val BASE_URL = "https://api.open-meteo.com/v1/forecast"
        private const val API_PARAMS = "temperature_2m,relative_humidity_2m,apparent_temperature,precipitation_probability,pressure_msl,surface_pressure,uv_index,wind_speed_10m,weather_code&hourly=temperature_2m,weather_code&daily=weather_code,temperature_2m_max,temperature_2m_min"

    }
}