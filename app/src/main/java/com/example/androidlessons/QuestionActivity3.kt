package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity3 : AppCompatActivity() {

    private var score: Int = 0

    private lateinit var questionText: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        questionText = findViewById(R.id.questionText)
        radioGroup = findViewById(R.id.optionsRG)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)

        score = intent.getIntExtra("score", 0)

        questionText.text = "Когда была подписана Конституция Российской Федерации?"
        option1.text = "1991"
        option2.text = "2000"
        option3.text = "1993" // true


        radioGroup.setOnCheckedChangeListener { group, checkedid ->
            val radio: RadioButton = findViewById(checkedid)
            if (radio.id == option3.id) {
                score += 100
            }
            val intent = Intent(this, QuestionActivity4::class.java)
            intent.putExtra("score", score)
            startActivity(intent)
            finish()
        }

    }
}