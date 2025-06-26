package com.elhady.weather_compose.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("current")
    val current: CurrentWeatherDto?,
    @SerialName("current_units")
    val currentUnits: CurrentUnitsDto?,
    @SerialName("elevation")
    val elevation: Double?,
    @SerialName("generationtime_ms")
    val generationTimeMs: Double?,
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
    @SerialName("hourly")
    val hourlyData: HourlyDataDto,
    @SerialName("daily")
    val dailyData: DailyDataDto
)