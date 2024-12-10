package com.example.androidlessons

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val exercises = ExerciseDataBase.exercises

    private lateinit var exerciseMainTV : TextView
    private lateinit var sloganTV: TextView
    private lateinit var goExerciseButtonBTN: Button
    private lateinit var imageViewMainIV: ImageView


    private lateinit var toolbar: androidx.appcompat.widget.Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        title = "Тренировки по фитнесу"
        setSupportActionBar(toolbar)

        exerciseMainTV.text = exercises[0].name

        goExerciseButtonBTN.setOnClickListener{
            startActivity(Intent(this, ExerciseActivity::class.java))
        }

    }

    private fun init() {
        exerciseMainTV = findViewById(R.id.exerciseMainTV)
        sloganTV = findViewById(R.id.sloganTV)
        goExerciseButtonBTN = findViewById(R.id.goExerciseButtonBTN)
        imageViewMainIV = findViewById(R.id.imageViewMainIV)
        toolbar = findViewById(R.id.toolbarMain)
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