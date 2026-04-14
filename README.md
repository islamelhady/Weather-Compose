# 🌤️ Weather Compose

A modern Android weather application built with **Jetpack Compose**, following best practices and a clean architecture approach.

## 🚀 Tech Stack

*   **Jetpack Compose:** For building a reactive and modern UI.
*   **Koin:** Lightweight dependency injection framework.
*   **Ktor Client:** For handling network requests and API communication.
*   **Kotlinx Serialization:** For parsing JSON responses into Kotlin objects.
*   **MVVM Architecture:** Ensuring a clear separation of concerns.
*   **Clean Architecture:** Structured into Data, Domain, and Presentation layers.
*   **Coroutines & Flow:** For asynchronous programming and reactive data streams.
*   **Play Services Location:** For fetching the user's current coordinates.
*   **Accompanist Permissions:** Seamless permission management in Compose.

## ✨ Features

*   **Automatic Location:** Get real-time weather data based on your current GPS location.
*   **Current Weather:** Detailed information including temperature, humidity, wind speed, pressure, and UV index.
*   **Hourly Forecast:** Visualized weather updates for the next 24 hours.
*   **7-Day Forecast:** Daily weather predictions for the upcoming week.
*   **Dynamic UI:** Fully supports Light and Dark modes, with UI elements that adapt to the time of day and weather conditions.
*   **Error Handling:** Robust error states and loading indicators with "Retry" functionality.

## 🛠️ Project Structure

The project follows **Clean Architecture** principles:
- **Data:** Contains DTOs, Repository implementations, and the API client.
- **Domain:** Contains core Business Logic (Entities, Repository Interfaces, and Use Cases).
- **Presentation:** Contains ViewModels, UI Screens, Composables, and Theme definitions.

## 🔌 Data Source

This app uses the [Open-Meteo API](https://open-meteo.com/) for high-resolution weather data.

## 📸 Screenshots

| Screen |
| :---: | |
| ![screen](D:\Android Projects\Weather-Compose\screenshot\screen.png) |