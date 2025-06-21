package com.elhady.weather_compose.data.dto

import kotlinx.serialization.SerialName

data class WeatherDto(
    @SerialName("current")
    val current: CurrentWeatherDto?,
    @SerialName("current_units")
    val currentUnits: CurrentUnitsDto?,
    @SerialName("elevation")
    val elevation: Double?,
    @SerialName("generationtime_ms")
    val generationtimeMs: Double?,
    @SerialName("latitude")
    val latitude: Double?,
    @SerialName("longitude")
    val longitude: Double?,
    @SerialName("timezone")
    val timezone: String?,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String?,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int?,
    @SerialName("hourly") // New property
    val hourlyData: HourlyDataDto
)