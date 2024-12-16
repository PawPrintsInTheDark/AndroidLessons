package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var startBTN: Button

    @SuppressLint("MissingInflatedId", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbarMain)
        startBTN = findViewById(R.id.startBTN)

        toolbar.title = "База данных"
        setSupportActionBar(toolbar)

        startBTN.setOnClickListener{
            startActivity(Intent(this, SecondActivity::class.java)).also { finish() }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAffinity()
        return super.onOptionsItemSelected(item)
    }
}