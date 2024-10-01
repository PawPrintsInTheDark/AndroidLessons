package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textTV : TextView
    private lateinit var rulesTV : TextView

    lateinit var infoCB : CheckBox

    @SuppressLint( "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textTV = findViewById(R.id.voidfield)
        rulesTV = findViewById(R.id.rulesTV)
        infoCB = findViewById(R.id.infoCB)

        infoCB.setOnCheckedChangeListener{_,isChecked ->
            if (isChecked){
                textTV.text = "Правила дорожного движения"
                rulesTV.text =
                        " 1. Соблюдайте скорость.\n" +
                        " 2. Используйте ремень безопасности.\n" +
                        " 3. Не управляйте автомобилем в состоянии алкогольного опьянения.\n" +
                        " 4. Соблюдайте правила дорожного движения.\n" +
                        " 5. Уважайте других участников дорожного движения."
            }else{
                textTV.text = "Информация"
                rulesTV.text = ""
            }
        }


    }


}