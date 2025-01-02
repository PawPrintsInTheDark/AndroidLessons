package com.example.androidlessons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _currencies = MutableLiveData<List<Currency>>()
    val currencies: LiveData<List<Currency>> get () = _currencies

    init {
        loadCurrencies()
    }

    private fun loadCurrencies() {
        _currencies.value = repository.getCurrencies()
    }
}
