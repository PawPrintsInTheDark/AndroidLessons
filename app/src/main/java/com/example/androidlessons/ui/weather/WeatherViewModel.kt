package com.example.androidlessons.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Home fragment"
    }
    val text: LiveData<String> = _text
}