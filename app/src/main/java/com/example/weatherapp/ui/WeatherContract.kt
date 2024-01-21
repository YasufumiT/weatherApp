package com.example.weatherapp.ui

import com.example.weatherapp.model.WeatherModel

interface WeatherContract {
    interface View {
        fun setWeatherInfo(weatherModel: WeatherModel)
        fun showDialog()
        fun setProgressBar(isShow: Boolean)
    }

    interface Presenter {
        fun getWeather()
    }
}
