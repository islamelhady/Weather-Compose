package com.elhady.weather_compose.data.dto

import kotlinx.serialization.SerialName

data class CurrentUnitsDto(
    @SerialName("interval")
    val interval: String?,
    @SerialName("temperature_2m")
    val temperature2m: String?,
    @SerialName("time")
    val time: String?,
    @SerialName("weather_code")
    val weatherCode: String?,
    @SerialName("wind_speed_10m")
    val windSpeed10m: String?
)