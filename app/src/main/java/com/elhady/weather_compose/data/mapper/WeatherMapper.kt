package com.elhady.weather_compose.data.mapper

import com.elhady.weather_compose.data.dto.WeatherDto
import com.elhady.weather_compose.domain.entities.DailyWeather
import com.elhady.weather_compose.domain.entities.HourlyWeather
import com.elhady.weather_compose.domain.entities.Weather
import com.elhady.weather_compose.domain.entities.WeatherType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
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

    val dailyData = this.dailyData.time.mapIndexed { index, dateString ->
        val date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE)
        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)

        val maxTemp = this.dailyData.maxTemperatures[index]
        val minTemp = this.dailyData.minTemperatures[index]
        val weatherCode = this.dailyData.weatherCodes[index]

        DailyWeather(
            day = dayOfWeek,
            maxTemp = maxTemp.roundToInt(),
            minTemp = minTemp.roundToInt(),
            weatherType = WeatherType.fromWMO(weatherCode)
        )
    }.drop(1)

    return Weather(
        temperature = data?.temperature2m ?: 0.0,
        windSpeed = data?.windSpeed10m ?: 0.0,
        humidity = data?.humidity ?: 0,
        pressure = data?.pressure?.toInt() ?: 0,
        feelsLike = data?.feelsLike ?: 0.0,
        uvIndex = data?.uvIndex ?: 0.0,
        rainChance = data?.rainChance ?: 0,
        weatherType = WeatherType.fromWMO(this.current?.weatherCode ?: 0),
        hourlyForecast = hourlyData,
        dailyForecast = dailyData
    )
}