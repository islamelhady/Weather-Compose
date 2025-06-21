package com.elhady.weather_compose.data.mapper

import com.elhady.weather_compose.data.dto.WeatherDto
import com.elhady.weather_compose.domain.entities.Weather
import com.elhady.weather_compose.domain.entities.WeatherType

fun WeatherDto.toWeather(): Weather{
    return Weather(
        temperature = this.current?.temperature2m ?: 0.0,
        windSpeed = this.current?.windSpeed10m ?: 0.0,
        weatherType = WeatherType.fromWMO(this.current?.weatherCode ?: 0)
    )
}