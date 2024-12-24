package com.example.androidlessons

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlessons.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val recentActivities = mutableListOf("Activity 1", "Activity 2", "Activity 3")
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, recentActivities)
        binding.recentActivitiesList.adapter = adapter

        binding.goToProfileButton.setOnClickListener {
            // тут могло что то быть но мне лень :р
        }
    }
}
