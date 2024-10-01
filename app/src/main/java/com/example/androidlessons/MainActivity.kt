package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var inputFieldET: EditText
    private lateinit var outputFieldTV: TextView
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button


    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        inputFieldET = findViewById(R.id.inputField)
        outputFieldTV = findViewById(R.id.outputField)
        saveButton = findViewById(R.id.saveButton)
        deleteButton = findViewById(R.id.deleteButton)

        saveButton.setOnClickListener {
            outputFieldTV.text = inputFieldET.text.toString()
        }
        deleteButton.setOnClickListener { view ->
            Snackbar
                .make(
                    view,
                    "Потвердить удаление?",
                    Snackbar.LENGTH_LONG
                )
                .setAction("Удалить?") {
                    Snackbar.make(
                        view,
                        "Данные удалены",
                        Snackbar.LENGTH_LONG
                    ).show()
                    outputFieldTV.text = ""
                }.show()
        }
    }


}