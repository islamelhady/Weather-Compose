package com.elhady.weather_compose.data.dto

import kotlinx.serialization.SerialName

data class HourlyDataDto(
    val time: List<String>,
    @SerialName("temperature_2m")
    val temperatures: List<Double>,
    @SerialName("weather_code")
    val weatherCodes: List<Int>
)