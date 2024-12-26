
package com.example.androidlessons.ui.personalinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonalInfoViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Личная информация"
    }
    val text: LiveData<String> = _text
}
