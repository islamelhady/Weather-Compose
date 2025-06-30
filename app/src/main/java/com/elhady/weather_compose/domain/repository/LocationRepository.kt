package com.elhady.weather_compose.domain.repository

import com.elhady.weather_compose.domain.entites.Location

interface LocationRepository {
    suspend fun getLocation(): Location
}