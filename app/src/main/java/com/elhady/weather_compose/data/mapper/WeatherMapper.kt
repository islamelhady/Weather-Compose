package com.elhady.weather_compose.data.mapper

import com.elhady.weather_compose.data.dto.WeatherDto
import com.elhady.weather_compose.domain.entities.HourlyWeather
import com.elhady.weather_compose.domain.entities.Weather
import com.elhady.weather_compose.domain.entities.WeatherType
import kotlin.math.roundToInt

fun WeatherDto.toWeather(): Weather {
    val data = this.current
    val hourlyData = this.hourlyData.time.mapIndexed { index, time ->
        val temperature = this.hourlyData.temperatures[index]
        val weatherCode = this.hourlyData.weatherCodes[index]
        HourlyWeather(
            time = time.substringAfter('T'),
            temperature = temperature.roundToInt(),
            weatherType = WeatherType.fromWMO(weatherCode)
        )
    }.take(24)

    return Weather(
        temperature = data?.temperature2m ?: 0.0,
        windSpeed = data?.windSpeed10m ?: 0.0,
        humidity = data?.humidity ?: 0,
        pressure = data?.pressure?.toInt() ?: 0,
        feelsLike = data?.feelsLike ?: 0.0,
        uvIndex = data?.uvIndex ?: 0.0,
        rainChance = data?.rainChance ?: 0,
        weatherType = WeatherType.fromWMO(this.current?.weatherCode ?: 0),
        hourlyForecast = hourlyData
    )
}