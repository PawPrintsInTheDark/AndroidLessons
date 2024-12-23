package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.androidlessons.utils.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class WeatherActivity : AppCompatActivity()  {

    private lateinit var toolbar: Toolbar
    private lateinit var temperatureTV: TextView
    private lateinit var cityTV: TextView
    private lateinit var weatherIV: ImageView
    private lateinit var windDegreeTV: TextView
    private lateinit var windSpeedTV: TextView
    private lateinit var pressureTV: TextView
    private lateinit var temperatureMinTV: TextView
    private lateinit var temperatureMaxTV: TextView
    private lateinit var humidityTV: TextView
    private lateinit var cityET: EditText
    private lateinit var getDataBTN: Button

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        toolbar = findViewById(R.id.toolbarWeather)
        setSupportActionBar(toolbar)

        temperatureTV = findViewById(R.id.temperatureTV)
        weatherIV = findViewById(R.id.weatherIV)
        cityTV = findViewById(R.id.cityTV)
        windDegreeTV = findViewById(R.id.windDegreeTV)
        windSpeedTV = findViewById(R.id.windSpeedTV)
        pressureTV = findViewById(R.id.pressureTV)

        temperatureMaxTV = findViewById(R.id.temperatureMaxTV)
        temperatureMinTV = findViewById(R.id.temperatureMinTV)
        humidityTV = findViewById(R.id.humidityTV)
        cityET = findViewById(R.id.cityET)
        getDataBTN = findViewById(R.id.getDataBTN)

        getDataBTN.setOnClickListener{
            if (cityET.text.toString().isNotEmpty()){
                getCurrentWeather()
            }else Toast.makeText(applicationContext, "Вы не указали город", Toast.LENGTH_SHORT).show()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun getCurrentWeather() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCurrentWeather(
                    "${cityET.text.toString()}",
                    "metric",
                    applicationContext.getString(R.string.api_key)
                )
            } catch (e:IOException){
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            }catch (e:HttpException){
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    val data = response.body()
                    Log.d("WeatherData", data.toString())
                    cityTV.text = data!!.name
                    temperatureTV.text = "${data.main.temp}°C"
                    windDegreeTV.text ="Ветер: ${data.wind.deg}°"
                    windSpeedTV.text = "${data.wind.speed} m/sec"
                    temperatureMaxTV.text = "Max: ${data.main.temp_max} °C"
                    temperatureMinTV.text = "Min: ${data.main.temp_min} °C"
                    humidityTV.text = "Влажность: ${data.main.humidity} %"
                    val iconId = data.weather[0].icon
                    val imageUrl = "https://openweathermap.org/img/wn/$iconId@4x.png"
                    Picasso.get().load(imageUrl).into(weatherIV)
                    val convertPressure = (data.main.pressure / 1.33).toInt()
                    pressureTV.text = "Давление: $convertPressure mm Hg"
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAffinity()
        return true
    }
}
