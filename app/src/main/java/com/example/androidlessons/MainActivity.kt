package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var createShopBTN: Button
    private lateinit var toolbarMain: androidx.appcompat.widget.Toolbar


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Supermarket"

        createShopBTN = findViewById(R.id.createShopBTN)
        createShopBTN.setOnClickListener{ startActivity(Intent(this, SecondActivity::class.java)).also { finish() }  }


    }

}