package com.elhady.weather_compose.data.dto

import kotlinx.serialization.SerialName

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
    val windSpeed10m: Double?
)