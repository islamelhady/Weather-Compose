package com.elhady.weather_compose.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

data class WeatherColorScheme(
    val primary: Color,
    val backgrounds: List<Color>,
    val variantColor: Color,
    val surface: Color,
    val surfaceTransparent87: Color,
    val surfaceTransparent70: Color,
    val surfaceTransparent60: Color,
    val surfaceTransparent8: Color,
    val oppositeColor: Color,
    val oppositeColorTransparent87: Color,
    val oppositeColorTransparent70: Color,
    val oppositeColorTransparent60: Color,
    val oppositeColorTransparent8: Color,
)

val lightWeatherColorScheme = WeatherColorScheme(
    primary = LightBlue,
    backgrounds = listOf(LightBlue, White),
    variantColor = DarkGray,
    surface = White,
    surfaceTransparent87 = WhiteTransparent87,
    surfaceTransparent70 = WhiteTransparent70,
    surfaceTransparent60 = WhiteTransparent60,
    surfaceTransparent8 = WhiteTransparent8,
    oppositeColor = Black,
    oppositeColorTransparent87 = BlackTransparent87,
    oppositeColorTransparent70 = BlackTransparent70,
    oppositeColorTransparent60 = BlackTransparent60,
    oppositeColorTransparent8 = BlackTransparent8,
)

val darkWeatherColorScheme = WeatherColorScheme(
    primary = LightBlue,
    backgrounds = listOf(Black, Black),
    variantColor = White,
    surface = Black,
    surfaceTransparent87 = BlackTransparent87,
    surfaceTransparent70 = BlackTransparent70,
    surfaceTransparent60 = BlackTransparent60,
    surfaceTransparent8 = BlackTransparent8,
    oppositeColor = White,
    oppositeColorTransparent87 = WhiteTransparent87,
    oppositeColorTransparent70 = WhiteTransparent70,
    oppositeColorTransparent60 = WhiteTransparent60,
    oppositeColorTransparent8 = WhiteTransparent8,
)

internal val LocalWeatherColors = staticCompositionLocalOf { lightWeatherColorScheme }
internal val LocalIsDay = staticCompositionLocalOf { true }

object MyWeatherTheme {
    val colors: WeatherColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalWeatherColors.current

    val isDay: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalIsDay.current
}

@Composable
fun WeatherTheme(
    isDay: Boolean = !isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDay) lightWeatherColorScheme else darkWeatherColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDay
        }
    }

    CompositionLocalProvider(
        LocalWeatherColors provides colors,
        LocalIsDay provides isDay,
        content = content
    )
}