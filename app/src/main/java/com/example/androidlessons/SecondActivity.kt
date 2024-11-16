package com.example.androidlessons

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var imgIV: ImageView
    private lateinit var btnBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        imgIV = findViewById(R.id.imgIV)
        btnBTN = findViewById(R.id.button)

        imgIV.clipToOutline = true

        val images = arrayOf(R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5)
        var currentIndex = 0

        btnBTN.setOnClickListener {
            if (currentIndex == 4) {
                startActivity(Intent(this, ThirdActivity::class.java)).also { finish() }
            }else {
                imgIV.setImageResource(images[currentIndex++ % images.size])
            }
        }




    }

}