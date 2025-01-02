package com.example.androidlessons

import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor() : CurrencyRepository {
    override fun getCurrencies(): List<Currency> {
        return listOf(
            Currency("Биткоин", R.drawable.ic_bit),
            Currency("Евро", R.drawable.ic_eur),
            Currency("Рубль", R.drawable.ic_rub)
        )
    }
}
