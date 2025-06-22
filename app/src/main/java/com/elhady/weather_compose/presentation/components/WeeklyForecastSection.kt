package com.elhady.weather_compose.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.elhady.weather_compose.presentation.WeatherUIState
import com.elhady.weather_compose.ui.theme.CardLight

@Composable
fun WeeklyForecastSection(
    state: WeatherUIState,
    modifier: Modifier = Modifier
) {
    state.weather?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Next 7 days",
                style = MaterialTheme.typography.titleSmall
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.large)
                    .background(CardLight.copy(alpha = 0.7f))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                        shape = MaterialTheme.shapes.large
                    )
                    .padding(vertical = 4.dp)
            ) {
                data.dailyForecast.forEachIndexed { index, weatherItem ->
                    DailyForecastItem(weather = weatherItem)
                    if (index < data.dailyForecast.lastIndex) {
                        Divider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                        )
                    }
                }
            }
        }
    }
}