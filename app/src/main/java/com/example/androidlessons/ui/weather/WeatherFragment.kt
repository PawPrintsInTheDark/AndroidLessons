package com.example.androidlessons.ui.weather

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidlessons.R
import com.example.androidlessons.databinding.FragmentWeatherBinding
import com.example.androidlessons.models.CurrentWeather
import com.example.androidlessons.models.Weather
import com.example.androidlessons.utils.RetrofitInstance
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class WeatherFragment : Fragment() {
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        getCurrentLocation()
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                Log.e("location", "${it.latitude}, ${it.longitude}")
                getCurrentWeather(it.latitude, it.longitude)
            } ?: run {
                Log.e("location", "Не удалось получить местоположение")
                showToast("Не удалось получить местоположение")
            }
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    @SuppressLint("SetTextI18n")
    private fun getCurrentWeather(latitude: Double, longitude: Double) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCurrentWeatherByCoordinates(
                    latitude,
                    longitude,
                    "metric",
                    requireContext().getString(R.string.api_key)
                )
            } catch (e: IOException) {
                handleError(e.message)
                return@launch
            } catch (e: HttpException) {
                handleError(e.message)
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                updateUI(response.body()!!)
            } else {
                Log.e("WeatherError", "Response not successful: ${response.code()}")
                showToast("Ошибка получения данных о погоде")
            }
        }
    }

    private suspend fun handleError(message: String?) {
        withContext(Dispatchers.Main) {
            showToast("app error $message")
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun updateUI(data: CurrentWeather) {
        withContext(Dispatchers.Main) {
            Log.d("WeatherData", data.toString())
            binding.cityTV.text = data. name
            binding.temperatureTV.text = "${data.main.temp}°C"
            binding.windDegreeTV.text = "Ветер: ${data.wind.deg}°"
            binding.windSpeedTV.text = "${data.wind.speed} m/sec"
            binding.temperatureMaxTV.text = "Max: ${data.main.temp_max} °C"
            binding.temperatureMinTV.text = "Min: ${data.main.temp_min} °C"
            binding.humidityTV.text = "Влажность: ${data.main.humidity} %"
            val iconId = data.weather[0].icon
            val imageUrl = "https://openweathermap.org/img/wn/$iconId@4x.png"
            Picasso.get().load(imageUrl).into(binding.weatherIV)
            Log.d("ImageURL", imageUrl)
            val convertPressure = (data.main.pressure / 1.33).toInt()
            binding.pressureTV.text = "Давление: $convertPressure mm Hg"
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                showToast("Разрешение на доступ к местоположению отклонено")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}