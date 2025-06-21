package com.elhady.weather_compose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.elhady.weather_compose.domain.entities.HourlyWeather
import com.elhady.weather_compose.ui.theme.CardLight

@Composable
fun HourlyForecastItem(
    weather: HourlyWeather,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 88.dp, height = 120.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(CardLight.copy(alpha = 0.7f))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                shape = MaterialTheme.shapes.medium
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = weather.time,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Image(
                // TODO: You need to provide your own icons for each weather type
                painter = painterResource(id = weather.weatherType.iconRes),
                contentDescription = weather.weatherType.description,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "${weather.temperature}°C",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}