package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var bmiResult: TextView
    private lateinit var bmiImage: ImageView
    private lateinit var recommendations: TextView

    @SuppressLint("DefaultLocale", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        bmiResult = findViewById(R.id.bmiResult)
        bmiImage = findViewById(R.id.bmiImage)
        recommendations = findViewById(R.id.recommendations)

        val weight = intent.getDoubleExtra("weight", 0.0)
            val height = intent.getDoubleExtra("height",0.0)

        val bmi = weight / (height * height)
        bmiResult.text = String.format("Индекс массы тела: %.2f", bmi)

        when {
            bmi < 18.5 -> {
                bmiImage.setImageResource(R.drawable.thin)
                recommendations.text ="""
                    Вы худенький. 
                    Рекомендуется:
                    - Увеличить потребление калорий, добавив в рацион больше белков и углеводов.
                    - Включить в меню орехи, авокадо, масла и молочные продукты.
                    - Заниматься силовыми тренировками для наращивания мышечной массы.
                    - Консультироваться с врачом или диетологом для составления индивидуального плана питания.
                """.trimIndent()
            }
            bmi in 18.5..24.9 -> {
                bmiImage.setImageResource(R.drawable.slim)
                recommendations.text = """
                    У вас нормальный вес . 
                    Рекомендуется:
                    - Продолжать вести активный образ жизни.
                    - Соблюдать сбалансированное питание, включая фрукты, овощи, белки и злаки.
                    - Регулярно заниматься физической активностью, включая кардио и силовые тренировки.
                    - Следить за уровнем стресса и достаточным количеством сна.
                """.trimIndent()
            }
            bmi >= 25.0-> {
                bmiImage.setImageResource(R.drawable.plump)
                recommendations.text = """
                    У вас избыточный вес. 
                    Рекомендуется:
                    - Уменьшить потребление калорий, особенно из сахара и жиров.
                    - Включить в рацион больше овощей, фруктов и цельнозерновых продуктов.
                    - Заниматься физической активностью не менее 150 минут в неделю.
                    - Рассмотреть возможность консультации с врачом или диетологом для составления плана снижения веса.
                """.trimIndent()
            }
        }
    }
}
