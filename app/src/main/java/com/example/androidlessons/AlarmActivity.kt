package com.example.androidlessons

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class AlarmActivity : AppCompatActivity() {

    private lateinit var stopAlarmButtonBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        stopAlarmButtonBTN = findViewById(R.id.stopAlarmButtonBTN)
        stopAlarmButtonBTN.setOnClickListener{
            finish()
            exitProcess(0)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAffinity()
        exitProcess(0)
    }
}