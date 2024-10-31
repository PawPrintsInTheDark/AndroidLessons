package com.example.androidlessons

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var titleTextTB : androidx.appcompat.widget.Toolbar
    lateinit var startBTN : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleTextTB = findViewById(R.id.titleText)
        startBTN = findViewById(R.id.startButton)

        setSupportActionBar(titleTextTB)
        title = "Викторина по истории Отечества"

        startBTN.setOnClickListener{
            val intent = Intent(this, QuestionActivity1::class.java)
            startActivity(intent)

        }

    }

}