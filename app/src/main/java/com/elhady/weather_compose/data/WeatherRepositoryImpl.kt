package com.elhady.weather_compose.data

import com.elhady.weather_compose.data.dto.WeatherDto
import com.elhady.weather_compose.data.dto.toWeather
import com.elhady.weather_compose.domain.repository.WeatherRepository
import com.elhady.weather_compose.domain.entites.Location
import com.elhady.weather_compose.domain.entites.Weather
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherRepositoryImpl(private val client: HttpClient) : WeatherRepository {
    override suspend fun getWeather(location: Location): Weather {
        val response = client.get("https://api.open-meteo.com/v1/forecast") {
            parameter("latitude", location.latitude)
            parameter("longitude", location.longitude)
            parameter("current", "temperature_2m,relative_humidity_2m,uv_index,is_day,rain,weather_code,surface_pressure,wind_speed_10m")
            parameter("daily", "temperature_2m_max,temperature_2m_min,weather_code")
            parameter("hourly", "temperature_2m,weather_code")
            parameter("timezone", "auto")
            parameter("forecast_days", 8)
        }
        
        return response.body<WeatherDto>().toWeather(location.city)
    }
}