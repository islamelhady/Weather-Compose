package com.elhady.weather_compose.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    @SerialName("interval")
    val interval: Int?,
    @SerialName("temperature_2m")
    val temperature2m: Double?,
    @SerialName("time")
    val time: String?,
    @SerialName("weather_code")
    val weatherCode: Int?,
    @SerialName("wind_speed_10m")
    val windSpeed10m: Double?,
    @SerialName("relative_humidity_2m")
    val humidity: Int,
    @SerialName("pressure_msl")
    val pressure: Double,
    @SerialName("apparent_temperature")
    val feelsLike: Double,
    @SerialName("uv_index")
    val uvIndex: Double,
    @SerialName("precipitation_probability")
    val rainChance: Int
)