package com.example.weatherapp.model

class WeatherRepository(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : IWeatherRepository {
    override suspend fun featchWeather(): WeatherModel? = weatherRemoteDataSource.featchWeather()
}
