package com.example.androidlessons

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.androidlessons.databinding.ActivityBotomNavBinding

class BottomNavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBotomNavBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityBotomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        backgroundAnimation()
        val navController = findNavController(R.id.navHostFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.WeatherFragment,
                R.id.personaInfoFragment,
                R.id.noteFragment,

                )
        )
        setupActionBarWithNavController(navController,appBarConfiguration)
        binding.bottomNavigationView.setupWithNavController(navController)

    }

    private fun backgroundAnimation() {
        val animation: AnimationDrawable = binding.main.background as AnimationDrawable
        animation.apply {
            setEnterFadeDuration(500)
            setExitFadeDuration(1500)
            start()
        }
    }
}