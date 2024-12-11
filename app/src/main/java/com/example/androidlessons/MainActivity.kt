package com.example.androidlessons

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var calendar: Calendar? = null
    private var materialTimePicker: MaterialTimePicker? = null

    private lateinit var setAlarmTV: TextView
    private lateinit var alarmButtonBTN: Button


    private lateinit var toolbar: androidx.appcompat.widget.Toolbar


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())


        init()
        toolbar.title = "Будильник"
        setSupportActionBar(toolbar)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // для отображения светлой темы

        alarmButtonBTN.setOnClickListener {
            materialTimePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Выберите время будильника")
                .build()
            materialTimePicker!!.addOnPositiveButtonClickListener {
                calendar = Calendar.getInstance()
                calendar?.set(Calendar.SECOND, 0)
                calendar?.set(Calendar.MILLISECOND, 0)
                calendar?.set(Calendar.MINUTE, materialTimePicker!!.minute)
                calendar?.set(Calendar.HOUR, materialTimePicker!!.hour)

                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

                alarmManager.setExact(
                    RTC_WAKEUP,
                    calendar?.timeInMillis!!,
                    getAlarmPendingIntent()!!
                )
                setAlarmTV.text = "Будильник установлен на ${dateFormat.format(calendar!!.time)}"
            }
            materialTimePicker!!.show(supportFragmentManager, "tag_picker")
        }

    }

    private fun getAlarmPendingIntent(): PendingIntent? {
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getBroadcast(
            this,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun init() {
        toolbar = findViewById(R.id.toolbarMain)
        alarmButtonBTN = findViewById(R.id.alertButtonBTN)
        setAlarmTV = findViewById(R.id.setAlarmTV)
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