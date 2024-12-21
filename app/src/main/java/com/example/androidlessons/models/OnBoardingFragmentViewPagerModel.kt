package com.example.androidlessons.models

import com.example.androidlessons.R
import java.io.Serializable

class OnBoardingFragmentViewPagerModel(
    val title: String,
    val imageView: Int,
    val checkedButton: Boolean
) : Serializable {
    companion object {
        val viewPagerList = mutableListOf(
            OnBoardingFragmentViewPagerModel(
                "Добро пожаловать в наш Мобильный банк,\n Узнайте о возможностях нашего мобильного приложения .",
                R.drawable.im1,
                false
            ),
            OnBoardingFragmentViewPagerModel(
                "Удобные функции, проверьте баланс, переводы и платежи в одно касание.",
                R.drawable.im2,
                false),
            OnBoardingFragmentViewPagerModel(
                "Начните использовать приложение, Нажмите 'Начать', чтобы зарегистрироваться и получить доступ к вашему счету.",
                R.drawable.im3,
                true),
        )
    }
}
