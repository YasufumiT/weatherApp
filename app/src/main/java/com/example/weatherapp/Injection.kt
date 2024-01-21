package com.example.weatherapp

import com.example.weatherapp.model.WeatherRemoteDataSource
import com.example.weatherapp.model.WeatherRepository

// 一旦作成したけど使用してない。。。
object Injection {
    fun getWeatherRepository() = WeatherRepository(WeatherRemoteDataSource())
}