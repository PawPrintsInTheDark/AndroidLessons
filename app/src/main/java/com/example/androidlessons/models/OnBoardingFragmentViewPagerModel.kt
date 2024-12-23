package com.example.androidlessons.models

import com.example.androidlessons.R
import java.io.Serializable

class OnBoardingFragmentViewPagerModel(
    val title: String,
    val imageView: Int,
    val checkedButton: Boolean = false
): Serializable {

    companion object{
        val viewPagerList = mutableListOf(
            OnBoardingFragmentViewPagerModel("Добро пожаловать в приложение Погода", R.drawable.weather_app,false),
            OnBoardingFragmentViewPagerModel("Узнайте погоду в вашем городе", R.drawable.weather_searth,false),
            OnBoardingFragmentViewPagerModel("Получите актуальные данные о погоде", R.drawable.actual_weather_data,true),
        )
    }
}