package com.example.androidlessons

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    private lateinit var input1: EditText
    private lateinit var input2: EditText

    private var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)

        val addButton: Button = findViewById(R.id.addButton)
        val subtractButton: Button = findViewById(R.id.subtractButton)
        val multiplyButton: Button = findViewById(R.id.multiplyButton)
        val divideButton: Button = findViewById(R.id.divideButton)
        val sendResultButton: Button = findViewById(R.id.sendResultButton)

        addButton.setOnClickListener { Calculate("+") }
        subtractButton.setOnClickListener { Calculate("-") }
        multiplyButton.setOnClickListener { Calculate("*") }
        divideButton.setOnClickListener { Calculate("/") }

        sendResultButton.setOnClickListener {
            if (result.isNaN()) return@setOnClickListener
            val intent = Intent()
            intent.putExtra("result", result.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

    private fun Calculate(operation: String) {
        val num1 = input1.text.toString().toDoubleOrNull()
        val num2 = input2.text.toString().toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Пожалуйста, введите корректные числа", Toast.LENGTH_SHORT).show()
            return
        }

        result = when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "Деление на ноль невозможно", Toast.LENGTH_SHORT).show()
                    return
                }
                num1 / num2
            }

            else -> Double.NaN
        }
    }
}