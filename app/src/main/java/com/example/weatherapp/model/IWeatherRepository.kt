package com.example.weatherapp.model

import com.example.weatherapp.model.WeatherModel

interface IWeatherRepository {
    suspend fun featchWeather(): WeatherModel?
}
