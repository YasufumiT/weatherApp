package com.example.weatherapp.ui.presenter

import com.example.weatherapp.ui.WeatherContract
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.model.WeatherRemoteDataSource
import com.example.weatherapp.model.WeatherRepository
import kotlinx.coroutines.*

class WeatherPresenter(
    private val view: WeatherContract.View,
) : WeatherContract.Presenter {

    // Repositoryを指定
    private var weaherRepository: WeatherRepository = WeatherRepository(WeatherRemoteDataSource())

    // アプリ起動したら天気情報取得を実施
    init {
        getWeather()
    }

    // 天気取得処理（取得成功したら取得情報をUIに反映し、失敗したら失敗ダイアログを表示）
    override fun getWeather() {
        var result: WeatherModel? = null
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                withContext(Dispatchers.Main) {
                    view.setProgressBar(true)
                }
                result = weaherRepository.featchWeather()
            }.onSuccess {
                result?.let {
                    withContext(Dispatchers.Main) {
                        view.setProgressBar(false)
                        view.setWeatherInfo(it)
                    }
                    return@let
                }
            }.onFailure {
                withContext(Dispatchers.Main) {
                    view.showDialog()
                    view.setProgressBar(false)
                }
            }
        }
    }
}
