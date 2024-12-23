package com.example.androidlessons

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WorkActivity : AppCompatActivity() {
    private lateinit var outputText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)

        outputText = findViewById(R.id.outputText)

        // Анимация для текста
        val shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake)
        outputText.startAnimation(shakeAnimation)

    }
}