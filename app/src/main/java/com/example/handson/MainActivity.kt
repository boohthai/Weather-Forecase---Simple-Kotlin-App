package com.example.handson

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val getForecastButton = findViewById<Button>(R.id.forecastButton)
        getForecastButton.setOnClickListener {
            val moveIntent = Intent(this, ForecastActivity::class.java)
            val location = findViewById<EditText>(R.id.cityName)
            moveIntent.putExtra("searchTerm", location.text.toString())
            startActivity(moveIntent)
        }
    }
}