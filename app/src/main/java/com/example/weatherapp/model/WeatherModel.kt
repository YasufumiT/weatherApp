package com.example.weatherapp.model

data class WeatherModel(
    val placeName: String,
    val weatherInfo: String,
) {
    val place: String = placeName
    val weather: String = weatherInfo
}
