package com.example.androidlessons

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlessons.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            saveSettings()
        }
    }

    private fun saveSettings() {
       // какая то логика
    }
}
