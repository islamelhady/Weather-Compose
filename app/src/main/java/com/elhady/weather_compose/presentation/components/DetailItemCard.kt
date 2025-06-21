package com.elhady.weather_compose.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.elhady.weather_compose.ui.theme.CardLight
import com.elhady.weather_compose.ui.theme.RangeBackgroundLight

data class WeatherDetail(
    val label: String,
    val value: String,
    @DrawableRes val iconRes: Int
)

@Composable
fun DetailItemCard(
    detail: WeatherDetail,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 108.dp, height = 115.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(CardLight.copy(alpha = 0.7f))
            .border(
                width = 1.dp,
                color = RangeBackgroundLight,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 8.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = detail.iconRes),
                contentDescription = detail.label,
                modifier = Modifier.size(32.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = detail.value,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = detail.label,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}