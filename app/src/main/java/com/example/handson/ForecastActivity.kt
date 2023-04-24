package com.example.handson

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        val searchTerm = intent.extras?.getString("searchTerm").toString()
        println(searchTerm)
        val retriever = WeatherRetriever()
        val callback = object : Callback<Weather> {
            override fun onResponse(
                call: Call<Weather>,
                response: Response<Weather>
            ) {
//                println(response?.body()?.forecast?.forecastday)
                val listView = findViewById<ListView>(R.id.forecastListView)
                val forecasts = response?.body()?.forecast?.forecastday

                var forecastString = mutableListOf<String>()
                if (forecasts != null) {
                    for (forecast in forecasts) {
                        println(forecast)
                        val newValueForecast = "${forecast?.date} \n High: ${forecast?.day?.maxtemp_c} \n Low: ${forecast?.day?.mintemp_c} \n Condition: ${forecast?.day?.condition?.text}"
                        forecastString.add(newValueForecast)
                    }
                }
                val adapter = ArrayAdapter(this@ForecastActivity, android.R.layout.simple_list_item_1,forecastString )
                listView.adapter = adapter
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                println("Fail")
            }

        }
        retriever.getForecast(callback, searchTerm, 10)
    }
}