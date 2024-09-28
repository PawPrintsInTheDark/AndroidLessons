package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalTime

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var toolbarMain: androidx.appcompat.widget.Toolbar

    private lateinit var firstOperandEV: EditText
    private lateinit var secondOperandEV: EditText

    private lateinit var sumBTN: Button
    private lateinit var difBTN: Button

    private lateinit var resultTV: TextView


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    @SuppressLint("ResourceAsColor")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.resetMenuMain -> {
                firstOperandEV.text.clear()
                secondOperandEV.text.clear()
                resultTV.setTextColor(resources.getColor(R.color.black, theme))
                resultTV.text = "Результат"
                Toast.makeText(applicationContext, "Данные очищены", Toast.LENGTH_SHORT).show()
            }
            R.id.exitMenuMain -> {
                finish()
                Toast.makeText(applicationContext, "Приложение закрыто", Toast.LENGTH_SHORT).show()

            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "  Калькулятор времени"
        toolbarMain.subtitle = "   v0.1"
        toolbarMain.setLogo(R.drawable.baseline_access_time_24)


        firstOperandEV = findViewById(R.id.firstOperandET)
        secondOperandEV = findViewById(R.id.secondOperandET)
        sumBTN = findViewById(R.id.sumBTN)
        difBTN = findViewById(R.id.difBTN)
        resultTV = findViewById(R.id.resultRV)

        sumBTN.setOnClickListener(this)
        difBTN.setOnClickListener(this)


    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View) {
        val time1 = timeParse(firstOperandEV.text.toString())
        val time2 = timeParse(secondOperandEV.text.toString())
        val res = when (v.id) {
            R.id.sumBTN -> {
                time1?.plusHours(time2?.hour?.toLong() ?: 0)
                    ?.plusMinutes(time2?.minute?.toLong() ?: 0)
                    ?.plusSeconds(time2?.second?.toLong() ?: 0)
            }

            R.id.difBTN -> {
                time1?.minusHours(time2?.hour?.toLong() ?: 0)
                    ?.minusMinutes(time2?.minute?.toLong() ?: 0)
                    ?.minusSeconds(time2?.second?.toLong() ?: 0)
            }

            else -> LocalTime.of(0, 0, 0)
        }

        resultTV.setTextColor(resources.getColor (R.color.color6task, theme))
        resultTV.text = formatTime(res)
        Toast.makeText(applicationContext, "Результат: ${formatTime(res)}", Toast.LENGTH_SHORT).show()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun timeParse(input: String): LocalTime? {
        if (input.isEmpty()) return LocalTime.of(0,0,0)
        var hours = 0
        var minutes =0
        var seconds = 0
        val matchResult = "(\\d+)(?=h)|(\\d+)(?=m)|(\\d+)(?=s)".toRegex().findAll(input)

        for (match in matchResult) {
            when {
                match.groups[1]?.value.takeIf { (it?.toInt() ?: 0) > 0 && (it?.toInt() ?: 0) < 24} != null -> hours = match.groups[1]!!.value.toInt()
                match.groups[2]?.value.takeIf { (it?.toInt() ?: 0) > 0 && (it?.toInt() ?: 0) < 59} != null -> minutes = match.groups[2]!!.value.toInt()
                match.groups[3]?.value.takeIf { (it?.toInt() ?: 0) > 0 && (it?.toInt() ?: 0) < 59} != null -> seconds = match.groups[3]!!.value.toInt()
            }
        }
        return LocalTime.of(hours,minutes,seconds)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun formatTime(time: LocalTime?): String {
        if (time == null) return "0s"
        return buildString {
            if (time.hour > 0) append("${time.hour}h")
            if (time.minute > 0) append("${time.minute}m")
            if (time.second > 0) append("${time.second}s")
        }.ifEmpty { "0s" }

    }
}