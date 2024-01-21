package com.example.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.ui.presenter.WeatherPresenter

class MainActivity : AppCompatActivity(), WeatherContract.View {

    // Presenterを指定
    private lateinit var presenter: WeatherContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = WeatherPresenter(this)

        val btnGetWeather: Button = findViewById(R.id.btnGetWeather)
        val btnClear: Button = findViewById(R.id.btnClear)

        //東京ボタンクリック時の処理
        btnGetWeather.setOnClickListener {
            presenter.getWeather()
        }

        //クリアボタンクリック時の処理
        btnClear.setOnClickListener {
            val place: TextView = findViewById(R.id.placeName)
            val weather: TextView = findViewById(R.id.weather)
            place.text = "場所"
            weather.text = "天気"
        }
    }

    //画面のテキストにJsonの取得結果（天気情報）を表示
    override fun setWeatherInfo(weatherModel: WeatherModel) {
        val place: TextView = findViewById(R.id.placeName)
        val weather: TextView = findViewById(R.id.weather)
        place.text = "${weatherModel.name}の天気は"
        weather.text = "${weatherModel.weather}です。"
    }

    // 取得失敗時のエラーダイアログ表示処理
    override fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle("取得失敗")
            .setMessage("天気情報の取得に失敗しました")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    // 情報取得中のプログレスバー表示処理
    override fun setProgressBar(isShow: Boolean) {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.isVisible = isShow
    }
}
