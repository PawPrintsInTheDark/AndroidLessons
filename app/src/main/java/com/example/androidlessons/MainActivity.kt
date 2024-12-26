package com.example.androidlessons

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.androidlessons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.duration = 6000

        binding.mainLayout.startAnimation(fadeOut)

        Handler().postDelayed({
            val intent = Intent(this@MainActivity, BottomNavActivity::class.java)
            startActivity(intent)
            finish()
        }, 3500)

    }
}

