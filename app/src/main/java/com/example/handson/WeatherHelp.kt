package com.example.handson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("forecast.json")
    fun getWeatherData(
        @Query("key") apiKey: String?,
        @Query("q") location: String?,
        @Query("days") days: Int,
        @Query("aqi") includeAqi: String?,
        @Query("alerts") includeAlerts: String?
    ): Call<Weather>
}
class Weather(val forecast: Forecast)
class Forecast(val forecastday: List<ForecastDay>)
class ForecastDay(val date: String, val day: DetailData)
class DetailData(val maxtemp_c: Float, val mintemp_c: Float, val condition: Condition)
class Condition(val text: String)
//class Forecast(val forecastday: List<ForecastDay>)
//class ForecastDay(val date: String, val date_epoch: String, val day: String , val astro: String, val hour: String)
class WeatherRetriever {
    val service : WeatherAPI
    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.weatherapi.com/v1/").addConverterFactory(GsonConverterFactory.create()).build()
        service  = retrofit.create(WeatherAPI::class.java)
    }

    fun getForecast(callback: Callback<Weather>, location: String, numOfDays: Int) {
        val call: Call<Weather> = service.getWeatherData(
            "b51e68e61d9949a7b6a84908232104",  // API key
            location,  // Location
            numOfDays,  // Number of days
            "no",  // Include AQI
            "no" // Include alerts
        )
        call.enqueue(callback)
    }
}

