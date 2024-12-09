package com.example.androidlessons

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date


class SecondActivity : AppCompatActivity() {
    private lateinit var nameTV: TextView
    private lateinit var secondNameTV: TextView
    private lateinit var dateTV: TextView
    private lateinit var phoneTV: TextView
    private lateinit var imageIV: ImageView

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        init()
        toolbar.title = "Карточка данных"
        setSupportActionBar(toolbar)

        val person = intent.extras?.getSerializable("person") as Person

        nameTV.text = "Имя: ${person.name}"
        secondNameTV.text = "Фамилия: ${person.secondName}"
        phoneTV.text = "Тел.: ${person.phone}"
        if (person.image == "") imageIV.setImageURI(Uri.parse(person.image))

        val age = calculateAge(person.birthday)
        val (monthsUntilBirthday, daysUntilBirthday) = calculateTimeUntilBirthday(person.birthday)

        dateTV.text = "Возраст: $age лет\nДо дня рождения: $monthsUntilBirthday мес. и $daysUntilBirthday дня(ей)"

    }

    private fun init() {
        toolbar = findViewById(R.id.toolbarSecond)
        nameTV = findViewById(R.id.textView)
        secondNameTV = findViewById(R.id.textView2)
        dateTV = findViewById(R.id.textView3)
        phoneTV = findViewById(R.id.textView4)
        imageIV = findViewById(R.id.editImageSecondIV)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateAge(birthDate: LocalDate): Int {
        return LocalDate.now().year - birthDate.year
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateTimeUntilBirthday(birthDate: LocalDate): Pair<Int, Int> {
        val today = LocalDate.now()
        val nextBirthday = getNextBirthday(birthDate, today)

        val monthsUntilBirthday = ChronoUnit.MONTHS.between(today, nextBirthday).toInt()
        val daysUntilBirthday = Period.between(today.plusMonths(monthsUntilBirthday.toLong()), nextBirthday).days

        return Pair(monthsUntilBirthday, daysUntilBirthday)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getNextBirthday(birthDate: LocalDate, today: LocalDate): LocalDate {
        return birthDate.withYear(today.year).let {
            if (it.isBefore(today) || it.isEqual(today)) it.plusYears(1) else it
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

