package com.elhady.weather_compose.presentation.composable.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elhady.weather_compose.presentation.composable.card.DailyForecastCard
import com.elhady.weather_compose.presentation.state.HourlyForecastState
import com.elhady.weather_compose.presentation.state.getFakeWeatherState
import com.elhady.weather_compose.presentation.theme.MyWeatherTheme
import com.elhady.weather_compose.presentation.theme.Urbanist
import com.elhady.weather_compose.presentation.theme.WeatherTheme

@Composable
fun ForecastForToday(
    hourlyForecastStates: List<HourlyForecastState>
) {
    Column {
        Text(
            text = "Today",
            fontFamily = Urbanist,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MyWeatherTheme.colors.oppositeColor,
            modifier = Modifier.padding(start = 12.dp, bottom = 12.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(hourlyForecastStates) { DailyForecastCard(it) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForecastForTodayPreview() {
    WeatherTheme {
        ForecastForToday(
            hourlyForecastStates = getFakeWeatherState().hourlyForecastStates
        )
    }
}