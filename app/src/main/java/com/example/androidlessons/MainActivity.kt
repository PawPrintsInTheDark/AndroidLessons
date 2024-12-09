package com.example.androidlessons

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.net.URI
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var nameET: EditText
    private lateinit var secondNameET: EditText
    private lateinit var dateET: EditText
    private lateinit var phoneET: EditText
    private lateinit var buttonBTN: Button
    private lateinit var imageIV: ImageView

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private var person : Person? = null
    private var selectedImg : Uri? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        toolbar.title = "Карточка данных"
        setSupportActionBar(toolbar)

        dateET.setOnClickListener {
            showDatePickerDialog()
        }

        imageIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, 1000)
        }

        buttonBTN.setOnClickListener {
            createPerson()
            if (person == null) return@setOnClickListener
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("person", person)
            startActivity(intent)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createPerson() {
        val firstName = nameET.text.toString()
        val lastName = secondNameET.text.toString()
        val birthDateStr = dateET.text.toString()
        val phone = phoneET.text.toString()

        if (firstName.isEmpty() || lastName.isEmpty() || birthDateStr.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }
        person = Person(
            firstName,
            lastName,
            LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
            phoneET.text.toString(),
            selectedImg.toString()
        )
    }

    @SuppressLint("DefaultLocale")
    private fun showDatePickerDialog() {
        // Получаем текущую дату
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Создаем диалог выбора даты
        val datePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // Устанавливаем выбранную дату в поле ввода
                val formattedDate =
                    String.format("%02d.%02d.%04d", selectedDay, selectedMonth + 1, selectedYear)
                dateET.setText(formattedDate)
            }, year, month, day)

        datePickerDialog.show()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            selectedImg = data?.data
            imageIV.setImageURI(selectedImg)
        }
    }

    private fun init(){
        toolbar = findViewById(R.id.toolbarMain)
        nameET = findViewById(R.id.EditTextMain)
        secondNameET = findViewById(R.id.EditTextMain2)
        dateET = findViewById(R.id.EditTextMain3)
        phoneET = findViewById(R.id.EditTextMain4)
        imageIV = findViewById(R.id.editImageMainIV)
        buttonBTN = findViewById(R.id.buttonMain)
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