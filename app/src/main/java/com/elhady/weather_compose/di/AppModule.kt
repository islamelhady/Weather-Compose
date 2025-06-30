package com.elhady.weather_compose.di

import com.elhady.weather_compose.data.LocationRepositoryImpl
import com.elhady.weather_compose.data.WeatherRepositoryImpl
import com.elhady.weather_compose.domain.GetWeatherUseCase
import com.elhady.weather_compose.domain.repository.LocationRepository
import com.elhady.weather_compose.domain.repository.WeatherRepository
import com.elhady.weather_compose.presentation.viewmodel.WeatherViewModel
import com.google.android.gms.location.LocationServices
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    single { LocationServices.getFusedLocationProviderClient(androidContext()) }

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<LocationRepository> { LocationRepositoryImpl(androidContext(), get()) }
    single { GetWeatherUseCase(get(), get()) }
    single { WeatherViewModel(get()) }
}