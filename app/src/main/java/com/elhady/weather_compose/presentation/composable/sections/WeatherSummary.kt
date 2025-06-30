package com.elhady.weather_compose.presentation.composable.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.elhady.weather_compose.presentation.composable.card.CityName
import com.elhady.weather_compose.presentation.composable.card.TemperatureInfo
import com.elhady.weather_compose.presentation.state.WeatherSummaryState
import com.elhady.weather_compose.presentation.state.wmoCodeToWeatherConditionState
import com.elhady.weather_compose.presentation.theme.WeatherTheme
import kotlin.math.min

@Composable
fun WeatherSummary(
    weatherSummaryState: WeatherSummaryState,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val scrollStateDp = with(density) { scrollState.value.toDp() }
    val state = min(scrollStateDp.value / 212, 1f) // from 0 to 1
    val screenWidth = LocalConfiguration.current.screenWidthDp
    var imageWidthDp by remember { mutableStateOf(0.dp) }
    var temperatureInfoWidthDp by remember { mutableStateOf(0.dp) }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CityName(
            weatherSummaryState.city,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    top = lerp(0.dp, 212.dp, state),
                    bottom = 12.dp
                )
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = weatherSummaryState.weatherConditionState.painter,
                contentDescription = null,
                modifier = Modifier
                    .height(lerp(200.dp, 112.dp, state))
                    .onGloballyPositioned { coordinates ->
                        imageWidthDp = with(density) { coordinates.size.width.toDp() }
                    }
                    .padding(top = lerp(0.dp, 15.5.dp, state))
                    .offset(
                        x = lerp(
                            ((screenWidth - imageWidthDp.value) / 2).dp,
                            12.dp,
                            state
                        )
                    )
            )
            TemperatureInfo(
                currentTemperature = weatherSummaryState.currentTemperature,
                maxTemperature = weatherSummaryState.maxTemperature,
                minTemperature = weatherSummaryState.minTemperature,
                weatherSummaryState.weatherConditionState.description,
                modifier = Modifier
                    .padding(top = lerp(212.dp, 0.dp, state))
                    .onGloballyPositioned { coordinates ->
                        temperatureInfoWidthDp = with(density) { coordinates.size.width.toDp() }
                    }
                    .offset(
                        x = lerp(
                            ((screenWidth - temperatureInfoWidthDp.value) / 2).dp,
                            (screenWidth - temperatureInfoWidthDp.value - 12).dp,
                            state
                        )
                    )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherSummaryPreview() {
    WeatherTheme {
        WeatherSummary(
            weatherSummaryState = WeatherSummaryState(
                city = "Cairo",
                currentTemperature = 32,
                maxTemperature = 32,
                minTemperature = 20,
                weatherConditionState = wmoCodeToWeatherConditionState(wmoCode = 1),
            ),
            scrollState = rememberScrollState()
        )
    }
}