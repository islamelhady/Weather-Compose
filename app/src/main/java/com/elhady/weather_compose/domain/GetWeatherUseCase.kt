package com.elhady.weather_compose.domain

import com.elhady.weather_compose.domain.repository.LocationRepository
import com.elhady.weather_compose.domain.repository.WeatherRepository
import com.elhady.weather_compose.domain.entites.Weather

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): Weather {
        val location = locationRepository.getLocation()
        return weatherRepository.getWeather(location)
    }
}