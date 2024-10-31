package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    private var score: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        score = intent.getIntExtra("score", 0)

        val resultText: TextView = findViewById(R.id.resultText)
        val descriptionText: TextView = findViewById(R.id.descriptionText)

        resultText.text = "Ваши баллы: $score"

        val description = when (score) {
            in 400..500 -> "Отличный знаток истории!"
            in 300..399 -> "Хороший знаток истории."
            in 200..299 -> "Удовлетворительный уровень знаний."
            in 100..199 -> "Низкий уровень знаний."
            else -> "Ваш уровень знаний истории на уровне хлебушка :)"
        }

        descriptionText.text = description

        val restartButton: Button = findViewById(R.id.restartButton)
        restartButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}