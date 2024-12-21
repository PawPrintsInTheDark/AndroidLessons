package com.example.androidlessons

import java.io.Serializable

class OnBoardingFragmentViewPagerModel(
    val title: String,
    val imageView: Int,
    val author: String
) : Serializable {
    companion object {
        val viewPagerList = mutableListOf(
            OnBoardingFragmentViewPagerModel(
                "Счастливый момент",
                R.drawable.i1, // Предполагается, что это изображение кота
                "Автор: Анна"
            ),
            OnBoardingFragmentViewPagerModel(
                "Теплый уют",
                R.drawable.i2, // Переименовано на i2
                "Автор: Сергей"
            ),
            OnBoardingFragmentViewPagerModel(
                "Нежное прикосновение",
                R.drawable.i3, // Переименовано на i3
                "Автор: Ольга"
            ),
            OnBoardingFragmentViewPagerModel(
                "Игривая радость",
                R.drawable.i4, // Переименовано на i4
                "Автор: Дмитрий"
            ),
            OnBoardingFragmentViewPagerModel(
                "Спокойствие",
                R.drawable.i5, // Переименовано на i5
                "Автор: Екатерина"
            ),
            OnBoardingFragmentViewPagerModel(
                "Улыбка дня",
                R.drawable.i6, // Переименовано на i6
                "Автор: Игорь"
            ),
            OnBoardingFragmentViewPagerModel(
                "Тихая гармония",
                R.drawable.i7, // Переименовано на i7
                "Автор: Мария"
            ),
            OnBoardingFragmentViewPagerModel(
                "Светлые мечты",
                R.drawable.i8, // Переименовано на i8
                "Автор: Алексей"
            ),
            OnBoardingFragmentViewPagerModel(
                "Мирное время",
                R.drawable.i9, // Переименовано на i9
                "Автор: Наталья"
            ),
            OnBoardingFragmentViewPagerModel(
                "Солнечный день",
                R.drawable.i10, // Переименовано на i10
                "Автор: Виктория"
            )
        )
    }
}
