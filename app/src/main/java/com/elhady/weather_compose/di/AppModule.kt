package com.elhady.weather_compose.di

import com.elhady.weather_compose.data.remote.WeatherApiService
import com.elhady.weather_compose.data.remote.WeatherApiServiceImpl
import com.elhady.weather_compose.data.repository.WeatherRepositoryImp
import com.elhady.weather_compose.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val appModule = module {

    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }

    single<WeatherApiService> {
        WeatherApiServiceImpl(get())
    }
    single<WeatherRepository> {
        WeatherRepositoryImp(get())
    }

}