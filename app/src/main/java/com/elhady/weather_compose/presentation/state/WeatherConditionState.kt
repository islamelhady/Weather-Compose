package com.elhady.weather_compose.presentation.state

import androidx.compose.ui.graphics.painter.Painter

data class WeatherConditionState (
    val description: String,
    val painter: Painter,
)