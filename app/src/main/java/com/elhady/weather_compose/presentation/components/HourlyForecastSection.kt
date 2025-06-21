package com.elhady.weather_compose.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elhady.weather_compose.presentation.WeatherUIState

@Composable
fun HourlyForecastSection(
    state: WeatherUIState,
    modifier: Modifier = Modifier
) {
    state.weather?.let { data ->
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Today",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(data.hourlyForecast) { weatherItem ->
                    HourlyForecastItem(weather = weatherItem)
                }
            }
        }
    }
}