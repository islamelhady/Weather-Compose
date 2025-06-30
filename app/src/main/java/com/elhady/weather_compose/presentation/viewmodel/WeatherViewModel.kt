package com.elhady.weather_compose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.weather_compose.domain.GetWeatherUseCase
import com.elhady.weather_compose.presentation.state.WeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weatherUiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val weatherUiState = _weatherUiState.asStateFlow()

    init {
        fetchWeatherData()
    }

    fun fetchWeatherData() {
        viewModelScope.launch {
            try {
                _weatherUiState.value = WeatherUiState.Loading
                val weatherData = getWeatherUseCase()
                _weatherUiState.value = WeatherUiState.Success(weatherData)
            } catch (e: Exception) {
                _weatherUiState.value = WeatherUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}