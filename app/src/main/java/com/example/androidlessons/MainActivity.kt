package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textTV: TextView
    private lateinit var downloadBTN: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        downloadBTN = findViewById(R.id.downloadButton)
        textTV = findViewById(R.id.textView)

        downloadBTN.setOnClickListener{

            for (w in loadBook(Database().text)){
                textTV.append(w + "\n")
            }
        }
    }


    private fun loadBook(text: String): List<String> {
        return text.split(" ")
    }


}