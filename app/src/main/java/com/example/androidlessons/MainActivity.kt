package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var characterCountTextView: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editText = findViewById(R.id.editTextED)
        resultTextView = findViewById(R.id.textTV2)
        characterCountTextView = findViewById(R.id.textTV3)
        button = findViewById(R.id.buttonBTN)

        onButtonClick(button)
    }

    fun onButtonClick(view : View){
        val inputText = editText.text.toString()
        val reversedText = inputText.reversed()
        resultTextView.text ="Результат:\n$reversedText"

        val characterCount = inputText.replace(" ", "").length
        characterCountTextView.text = "Количество символов: $characterCount"
    }

}