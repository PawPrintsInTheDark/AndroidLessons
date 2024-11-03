package com.example.androidlessons

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var firstNameTextView: TextView
    private lateinit var lastNameTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var phoneTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        firstNameTextView = findViewById(R.id.firstNameTextView)
        lastNameTextView = findViewById(R.id.lastNameTextView)
        addressTextView = findViewById(R.id.addressTextView)
        phoneTextView = findViewById(R.id.phoneTextView)

        val person = intent.getParcelableExtra<Person>("EXTRA_PERSON")

        person?.let {
            firstNameTextView.text = it.name
            lastNameTextView.text = it.lastname
            addressTextView.text = it.addr
            phoneTextView.text = it.num
        }

    }
}