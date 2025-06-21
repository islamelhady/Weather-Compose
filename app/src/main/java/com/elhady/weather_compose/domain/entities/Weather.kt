package com.elhady.weather_compose.domain.entities

data class Weather(
    val temperature: Double,
    val windSpeed: Double,
    val weatherType: WeatherType,
    val humidity: Int,
    val pressure: Int,
    val feelsLike: Double,
    val uvIndex: Double,
    val rainChance: Int

)

sealed class WeatherType(val description: String, val iconRes: Int) {
    data object ClearSky : WeatherType("Clear sky", /* TODO: Add drawable resource for sun icon */ 0)
    data object MainlyClear : WeatherType("Mainly clear", /* TODO: Add drawable resource */ 0)
    data object PartlyCloudy : WeatherType("Partly cloudy", /* TODO: Add drawable resource */ 0)
    data object Overcast : WeatherType("Overcast", /* TODO: Add drawable resource */ 0)
    // ... add other weather types as needed

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                else -> Overcast
            }
        }
    }
}
