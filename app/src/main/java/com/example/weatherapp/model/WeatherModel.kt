package com.example.weatherapp.model

data class WeatherModel(
    val cityName: String,
    val cityWeather: String,
) {
    val name: String = cityName
    val weather: String = cityWeather
}
