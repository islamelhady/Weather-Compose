package com.elhady.weather_compose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.weather_compose.data.location.LocationTrackerImp
import com.elhady.weather_compose.domain.location.LocationTracker
import com.elhady.weather_compose.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherUIState())
    val state = _state.asStateFlow()

    fun loadWeather() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val location = locationTracker.getCurrentLocation()
            if (location != null) {
                repository.getWeather(location.latitude, location.longitude)
                    .onSuccess { weather ->
                        _state.update {
                            it.copy(
                                weather = weather,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    .onFailure { throwable ->
                        _state.update {
                            it.copy(
                                weather = null,
                                isLoading = false,
                                error = "Error: $throwable.message"
                            )
                        }
                    }
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                    )
                }
            }
        }
    }
}