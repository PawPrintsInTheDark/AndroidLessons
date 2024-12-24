package com.example.androidlessons

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.androidlessons.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var list = mutableListOf(
        GridViewModal("Home", R.drawable.ic_home),
        GridViewModal("Chat", R.drawable.ic_chat),
        GridViewModal("Settings", R.drawable.ic_settings),
        GridViewModal("Logout", R.drawable.ic_logout),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBackground()

        val adapter = GridViewAdapter(list,this)
        binding.gridViewMainGV.adapter = adapter

        binding.gridViewMainGV.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
            when (position) {
                0 -> startActivity(Intent(this, HomeActivity::class.java)) // Home
                1 -> startActivity(Intent(this, ChatActivity::class.java)) // Chat
                2 -> startActivity(Intent(this, SettingsActivity::class.java)) // Settings
                3 -> finishAffinity()
            }
        }


    }

    private fun setBackground() {
        val animation: AnimationDrawable = binding.mainLayout.background as AnimationDrawable
        animation.apply {
            setEnterFadeDuration(500)
            setExitFadeDuration(1500)
            start()
        }
    }

}

