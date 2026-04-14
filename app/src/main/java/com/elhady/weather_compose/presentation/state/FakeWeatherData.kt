package com.elhady.weather_compose.presentation.state

import androidx.compose.runtime.Composable

@Composable
fun getFakeWeatherState(isDay: Boolean = false): WeatherState {
    return WeatherState(
        weatherSummaryState = WeatherSummaryState(
            city = "Cairo",
            weatherConditionState = wmoCodeToWeatherConditionState(1, isDay = isDay),
            currentTemperature = 10,
            maxTemperature = 20,
            minTemperature = 10
        ),
        currentWeatherDetailsState = CurrentWeatherDetailsState(
            windSpeed = 4,
            humidity = 93,
            rain = 0,
            uvIndex = 0,
            pressure = 1013,
            temperature = 10,
            isDay = isDay
        ),
        hourlyForecastStates = listOf(
            HourlyForecastState(
                temperature = "12",
                time = "00:00",
                weatherConditionState = wmoCodeToWeatherConditionState(1, isDay = isDay),
            ),
            HourlyForecastState(
                temperature = "13",
                time = "01:00",
                weatherConditionState = wmoCodeToWeatherConditionState(2, isDay = isDay),
            ),
            HourlyForecastState(
                temperature = "14",
                time = "02:00",
                weatherConditionState = wmoCodeToWeatherConditionState(3, isDay = isDay),
            ),
            HourlyForecastState(
                temperature = "13",
                time = "03:00",
                weatherConditionState = wmoCodeToWeatherConditionState(2, isDay = isDay),
            ),
            HourlyForecastState(
                temperature = "12",
                time = "04:00",
                weatherConditionState = wmoCodeToWeatherConditionState(1, isDay = isDay),
            ),
            HourlyForecastState(
                temperature = "11",
                time = "05:00",
                weatherConditionState = wmoCodeToWeatherConditionState(0, isDay = isDay),
            ),
        ),
        dailyForecastState = listOf(
            DailyForecastState(
                dayName = "Sunday",
                maxTemperature = 42,
                minTemperature = 30,
                weatherConditionState = wmoCodeToWeatherConditionState(1, isDay = isDay),
            ),
            DailyForecastState(
                dayName = "Monday",
                maxTemperature = 42,
                minTemperature = 30,
                weatherConditionState = wmoCodeToWeatherConditionState(2, isDay = isDay),
            ),
            DailyForecastState(
                dayName = "Tuesday",
                maxTemperature = 42,
                minTemperature = 30,
                weatherConditionState = wmoCodeToWeatherConditionState(0, isDay = isDay),
            ),
            DailyForecastState(
                dayName = "Wednesday",
                maxTemperature = 42,
                minTemperature = 30,
                weatherConditionState = wmoCodeToWeatherConditionState(51, isDay = isDay),
            ),
            DailyForecastState(
                dayName = "Thursday",
                maxTemperature = 42,
                minTemperature = 30,
                weatherConditionState = wmoCodeToWeatherConditionState(65, isDay = isDay),
            ),
            DailyForecastState(
                dayName = "Friday",
                maxTemperature = 42,
                minTemperature = 30,
                weatherConditionState = wmoCodeToWeatherConditionState(1, isDay = isDay),
            ),
            DailyForecastState(
                dayName = "Saturday",
                maxTemperature = 42,
                minTemperature = 30,
                weatherConditionState = wmoCodeToWeatherConditionState(1, isDay = isDay),
            ),
        )
    )
}