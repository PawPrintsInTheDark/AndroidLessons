package com.example.androidlessons

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var editTextET: EditText
    private lateinit var textViewResult: TextView
    private lateinit var gridLayout: GridLayout

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    @SuppressLint("MissingInflatedId", "UseSupportActionBar")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = "Калькулятор"
        toolbar.setTitleTextColor(Color.WHITE)


        editTextET = findViewById(R.id.editTextET)
        textViewResult = findViewById(R.id.textViewResult)

        // Настройка кнопок
        setupButtons()

    }

    private fun setupButtons() {
        gridLayout= findViewById(R.id.gridLayout)

        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            button.setOnClickListener {
                when (button.text) {
                    "=" -> calculateResult()
                    "reset" -> clearInput()
                    else -> appendToInput(button.text.toString())
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun appendToInput(value: String) {
        val currentInput = editTextET.text.toString()

        // Проверяем, является ли последний введённый символ операцией
        if (isOperator(value) && (currentInput.isEmpty() || isOperator(currentInput.last().toString()))) {
            return
        }

        editTextET.setText(currentInput + value)
    }

    private fun isOperator(value: String): Boolean {
        return value == "+" || value == "-" || value == "*" || value == "/"
    }


    @SuppressLint("SetTextI18n")
    private fun clearInput() {
        editTextET.setText("")
        textViewResult.text = "result"
    }

    private fun calculateResult() {
        val input = editTextET.text.toString()
        try {
            val result = evaluateExpression(input)
            textViewResult.text = result.toString()
        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка в выражении", Toast.LENGTH_SHORT).show()
        }
    }

    private fun evaluateExpression(expression: String): Double {
        val parser = ExpressionBuilder(expression).build()
        return parser.evaluate()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}