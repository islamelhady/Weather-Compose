package com.elhady.weather_compose.presentation.composable.card

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elhady.weather_compose.R
import com.elhady.weather_compose.presentation.extensionFunctions.dropShadow
import com.elhady.weather_compose.presentation.state.DailyForecastState
import com.elhady.weather_compose.presentation.state.WeatherConditionState
import com.elhady.weather_compose.presentation.theme.MyWeatherTheme
import com.elhady.weather_compose.presentation.theme.WeatherTheme
import com.elhady.weather_compose.presentation.theme.Urbanist

@Composable
fun WeeklyForecastItem(
    dailyForecastState: DailyForecastState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(61.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = dailyForecastState.dayName,
            fontFamily = Urbanist,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = MyWeatherTheme.colors.oppositeColorTransparent60,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .height(45.dp)
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = dailyForecastState.weatherConditionState.painter,
                contentDescription = null,
                modifier = Modifier
                    .height(32.dp)
                    .dropShadow(
                        shape = CircleShape,
                        scaleX = .8f,
                        scaleY = .5f,
                        offsetY = 10.dp,
                        offsetX = (-5).dp,
                        color = MyWeatherTheme.colors.oppositeColorTransparent8
                    )
            )
        }
        MaxMinTemperature(
            maxTemperature = dailyForecastState.maxTemperature,
            minTemperature = dailyForecastState.minTemperature,
            color = MyWeatherTheme.colors.oppositeColorTransparent87,
            modifier = Modifier.weight(1f)
        )

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun Preview() {
    WeatherTheme(isDay = true) {
        WeeklyForecastItem(
            DailyForecastState(
                dayName = "Monday",
                maxTemperature = 31,
                minTemperature = 18,
                weatherConditionState = WeatherConditionState(
                    "Clear sky",
                    painterResource(R.drawable.mainly_clear_day)
                )
            ),
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, backgroundColor = 0xFF060414)
@Composable
private fun DarkPreview() {
    WeatherTheme(isDay = false) {
        WeeklyForecastItem(
            DailyForecastState(
                dayName = "Sunday",
                maxTemperature = 32,
                minTemperature = 20,
                weatherConditionState = WeatherConditionState(
                    "Clear sky",
                    painterResource(R.drawable.mainly_clear_day)
                )
            ),
        )
    }
}