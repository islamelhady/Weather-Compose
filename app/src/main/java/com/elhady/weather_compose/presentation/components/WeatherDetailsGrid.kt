package com.elhady.weather_compose.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elhady.weather_compose.R
import com.elhady.weather_compose.presentation.WeatherUIState
import kotlin.math.roundToInt

@Composable
fun WeatherDetailsGrid(
    state: WeatherUIState,
    modifier: Modifier = Modifier
) {
    state.weather?.let { data ->
        val details = listOf(
            WeatherDetail("Wind", "${data.windSpeed.roundToInt()} km/h", R.drawable.ic_wind),
            WeatherDetail("Humidity", "${data.humidity}%", R.drawable.ic_humidity),
            WeatherDetail("Rain", "${data.rainChance}%", R.drawable.ic_rain),
            WeatherDetail("UV Index", "${data.uvIndex.roundToInt()}", R.drawable.ic_uv),
            WeatherDetail("Pressure", "${data.pressure} hPa", R.drawable.ic_pressure),
            WeatherDetail("Feels like", "${data.feelsLike.roundToInt()}°C", R.drawable.ic_feels_like)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(details) { detailItem ->
                DetailItemCard(detail = detailItem)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherDetailGridPreview(){
    WeatherDetailsGrid(WeatherUIState())
}