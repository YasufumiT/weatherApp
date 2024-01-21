package com.example.weatherapp.model

import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRemoteDataSource {
    //APIキーとURLの定義
    private val apiKey: String = "*****************"
    private val mainURL: String = "https://api.openweathermap.org"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(mainURL)
        .build()

    private val service = retrofit.create(WeatherService::class.java)
    private val loadRepos = service.loadRepos("ja", "tokyo", apiKey)

    fun featchWeather(): WeatherModel? = featchWeatherInfo()

    private fun featchWeatherInfo(): WeatherModel? {
        runCatching {
            loadRepos.clone().execute()
        }.onSuccess { responce ->
            if (responce.isSuccessful) {
                responce.body()?.string()?.let {
                    val jsonObj = JSONObject(it)

                    // モデルに取得したJsonの値を代入
                    return WeatherModel(
                        jsonObj.getString("name"),
                        jsonObj.getJSONArray("weather").getJSONObject(0).getString("description"),
                    )
                }
            } else {
                return null
            }
        }.onFailure {
            return null
        }
        return null
    }
}