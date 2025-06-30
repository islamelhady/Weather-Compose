package com.elhady.weather_compose.presentation.composable.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elhady.weather_compose.R
import com.elhady.weather_compose.di.appModule
import com.elhady.weather_compose.presentation.composable.card.WeatherInfoCard
import com.elhady.weather_compose.presentation.state.CurrentWeatherDetailsState
import com.elhady.weather_compose.presentation.viewmodel.WeatherViewModel
import org.koin.compose.getKoin
import org.koin.core.context.startKoin

@Composable
fun CurrentWeatherDetails(
    currentWeatherDetailsState: CurrentWeatherDetailsState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WeatherInfoCard(
                information = "${currentWeatherDetailsState.windSpeed} KM/h",
                description = "Wind",
                icon = painterResource(id = R.drawable.fast_wind_icon),
                modifier = Modifier.weight(1f)
            )
            WeatherInfoCard(
                information = "${currentWeatherDetailsState.humidity}%",
                description = "Humidity",
                icon = painterResource(id = R.drawable.humidity_icon),
                modifier = Modifier.weight(1f)
            )
            WeatherInfoCard(
                information = "${currentWeatherDetailsState.rain}%",
                description = "Rain",
                icon = painterResource(id = R.drawable.rain_icon),
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WeatherInfoCard(
                information = "${currentWeatherDetailsState.uvIndex}",
                description = "UV Index",
                icon = painterResource(id = R.drawable.uv_icon),
                modifier = Modifier.weight(1f)
            )
            WeatherInfoCard(
                information = "${currentWeatherDetailsState.pressure} hPa",
                description = "Pressure",
                icon = painterResource(id = R.drawable.pressure_icon),
                modifier = Modifier.weight(1f)
            )
            WeatherInfoCard(
                information = "${currentWeatherDetailsState.temperature}°C",
                description = "Feels like",
                icon = painterResource(id = R.drawable.temperature_icon),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CurrentWeatherDetailsPreview() {
    startKoin { modules(appModule) }
    CurrentWeatherDetails(
        currentWeatherDetailsState = CurrentWeatherDetailsState(
            windSpeed = 10,
            humidity = 10,
            rain = 3,
            uvIndex = 10,
            pressure = 10,
            temperature = 10,
            isDay = true
        )
    )
}