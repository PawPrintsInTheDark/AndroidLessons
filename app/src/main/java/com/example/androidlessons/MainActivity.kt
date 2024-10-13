package com.example.androidlessons

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    private lateinit var calculateBTN: Button
    private lateinit var weightET: EditText
    private lateinit var heightET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        calculateBTN = findViewById(R.id.calculateButton)
        weightET = findViewById(R.id.weightInput)
        heightET = findViewById(R.id.heightInput)

        calculateBTN.setOnClickListener {
            val weight = weightET.text.toString().toDoubleOrNull()
            val height = heightET.text.toString().toDoubleOrNull()?.div(100)

            if (weight != null && height != null) {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("weight", weight)
                intent.putExtra("height", height)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Данные введены не верно! ", Toast.LENGTH_SHORT).show()
            }
        }
    }



}