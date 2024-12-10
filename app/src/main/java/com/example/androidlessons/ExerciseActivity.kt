package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExerciseActivity : AppCompatActivity() {

    val exercises = ExerciseDataBase.exercises

    private lateinit var titleTV: TextView
    private lateinit var exerciseTV : TextView
    private lateinit var descriptionTV: TextView
    private lateinit var timerTV: TextView
    private lateinit var startButtonBTN: Button
    private lateinit var completedButtonBTN: Button
    private lateinit var imageViewIV: ImageView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    var exerciseIndex = 0
    private lateinit var currentExercise: Exercise
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        init()

        title = "Тренировки по фитнесу"
        setSupportActionBar(toolbar)


        startButtonBTN.setOnClickListener{
            startWork()
        }

        completedButtonBTN.setOnClickListener{
            completedExercise()
        }

    }

    private fun init() {
        titleTV = findViewById(R.id.titleTV)
        exerciseTV = findViewById(R.id.exerciseTV)
        descriptionTV = findViewById(R.id.descriptionTV)
        timerTV = findViewById(R.id.timerTV)
        startButtonBTN = findViewById(R.id.startButtonBTN)
        completedButtonBTN = findViewById(R.id.completedButtonBTN)
        imageViewIV = findViewById(R.id.imageViewIV)
        toolbar = findViewById(R.id.toolbarMain)
    }

    private fun completedExercise() {
        timer.cancel()
        completedButtonBTN.isEnabled = false
        startNextExercise()
    }

    private fun startWork() {
        exerciseIndex = 0
        titleTV.text = "Начало тренировки"
        startButtonBTN.isEnabled = false
        startButtonBTN.text = "Процесс тренировки"
        startNextExercise()
    }

    private fun startNextExercise() {
         if (exerciseIndex < exercises.size){
             currentExercise = exercises[exerciseIndex]
             exerciseTV.text = currentExercise.name
             descriptionTV.text = currentExercise.description
             imageViewIV.setImageResource(currentExercise.gifImage)
             timerTV.text = formatTime(currentExercise.durationInSeconds)
             timer = object : CountDownTimer(currentExercise.durationInSeconds * 1000L,1000){
                 override fun onTick(millisUntilFinished: Long) {
                    timerTV.text = formatTime((millisUntilFinished / 1000).toInt())
                 }

                 override fun onFinish() {
                    timerTV.text = "Упражнение завершено"
                     imageViewIV.visibility = View.VISIBLE
                     completedButtonBTN.isEnabled = true
                     imageViewIV.setImageResource(0)
                 }
             }.start()
             exerciseIndex++
         }else{
             exerciseTV.text = "Тренировка завершена"
             descriptionTV.text = ""
             timerTV.text = ""
             completedButtonBTN.isEnabled = false
             startButtonBTN.isEnabled = true
             startButtonBTN.text = "Начать снова"
         }
    }

    @SuppressLint("DefaultLocale")
    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
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