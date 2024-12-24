package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {

    private lateinit var shopImage: ImageView
    private lateinit var startShoppingButton: Button
    private lateinit var layout: LinearLayout

    @SuppressLint("CommitTransaction")


    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        shopImage = findViewById(R.id.shopImage)
        startShoppingButton = findViewById(R.id.startShoppingButton)

        layout = findViewById(R.id.mainLayout)
        BGAnimation.backgroundAnimation(layout)

        startShoppingButton.setOnClickListener {
            startShoppingButton.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(200)
                .withEndAction {
                    startShoppingButton.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .withEndAction {
                            startActivity(Intent(this, ProductListActivity::class.java))
                        }
                        .start()
                }
                .start()
        }
    }

}

