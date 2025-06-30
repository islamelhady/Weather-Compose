package com.elhady.weather_compose.presentation.state

data class WeatherState (
    val weatherSummaryState: WeatherSummaryState,
    val currentWeatherDetailsState: CurrentWeatherDetailsState,
    val hourlyForecastStates: List<HourlyForecastState>,
    val dailyForecastState: List<DailyForecastState>,
)