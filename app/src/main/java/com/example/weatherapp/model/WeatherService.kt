package com.example.weatherapp.model

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    //生成するURL：https://api.openweathermap.org/data/2.5/weather?lang=ja&q=tokyo&appid=*****************
    @GET("data/2.5/weather")
    fun loadRepos(
        @Query("lang") lang: String,
        @Query("q") q: String,
        @Query("appid") appid: String
    ): Call<ResponseBody>
}