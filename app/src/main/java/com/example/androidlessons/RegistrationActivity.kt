package com.example.androidlessons

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {


    private lateinit var registrationTitle: TextView
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var registerButton: Button
    private lateinit var linearLayoutLL: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        registrationTitle = findViewById(R.id.registrationTitle)
        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        registerButton = findViewById(R.id.registerButton)
        linearLayoutLL = findViewById(R.id.linearLayoutLL)

        val slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        registrationTitle.startAnimation(slideDown)

        val fadeInSlideUp = AnimationUtils.loadAnimation(this, R.anim.fadeslide_up)
        linearLayoutLL.startAnimation(fadeInSlideUp)

        registerButton.setOnClickListener {
            val intent = Intent(this, WorkActivity::class.java)
            startActivity(intent)
        }

    }
}