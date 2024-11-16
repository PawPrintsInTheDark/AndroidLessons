package com.example.androidlessons

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var btn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.startButton)

        btn.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java)).also { finish() }
        }

    }


}