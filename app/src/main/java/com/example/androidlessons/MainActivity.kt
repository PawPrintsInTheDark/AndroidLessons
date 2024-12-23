package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var startButton: Button

    @SuppressLint("CommitTransaction")

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imageView = findViewById(R.id.imageView)
        startButton = findViewById(R.id.startButton)

        val fadeIn = AnimationUtils.loadAnimation(applicationContext,R.anim.fade_in)
        imageView.startAnimation(fadeIn)

        val slideIn = AnimationUtils.loadAnimation(applicationContext,R.anim.slide_in)
         startButton.startAnimation(slideIn)

        startButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}

