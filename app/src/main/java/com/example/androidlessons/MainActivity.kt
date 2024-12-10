package com.example.androidlessons

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val exercises = ExerciseDataBase.exercises

    private lateinit var exerciseMainTV : TextView
    private lateinit var sloganTV: TextView
    private lateinit var goExerciseButtonBTN: Button
    private lateinit var imageViewIV: ImageView


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
        exerciseMainTV = findViewById(R.id.exerciseTV)
        sloganTV = findViewById(R.id.timerTV)
        goExerciseButtonBTN = findViewById(R.id.startButtonBTN)
        imageViewIV = findViewById(R.id.imageViewIV)
        toolbar = findViewById(R.id.toolbarMain)
    }
}