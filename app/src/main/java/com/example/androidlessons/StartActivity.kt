package com.example.androidlessons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlessons.fragment.SignUpFragment

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportFragmentManager.beginTransaction()
            .add(R.id.containerID, SignUpFragment())
            .commit()
    }
}