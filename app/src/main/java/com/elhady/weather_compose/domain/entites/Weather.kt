package com.elhady.weather_compose.domain.entites

data class Weather (
    val city: String,
    val currentUnits: CurrentUnits,
    val currentWeather: CurrentWeather,
    val hourlyUnits: HourlyUnits,
    val hourly: List<Hourly>,
    val dailyUnits: DailyUnits,
    val daily: List<Daily>
)