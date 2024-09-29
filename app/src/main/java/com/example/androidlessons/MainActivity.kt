package com.example.androidlessons

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarMain: androidx.appcompat.widget.Toolbar

    private lateinit var textET: EditText

    private lateinit var randomBTN: Button


    @SuppressLint("MissingInflatedId")
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
        title = "  Оценка за урок"
        toolbarMain.subtitle = "   v0.1"
        toolbarMain.setLogo(R.drawable.star)

        textET = findViewById(R.id.textET)

        randomBTN = findViewById(R.id.randomBTN)

        randomBTN.setOnClickListener {
            val r = Random.nextInt(1, 51)
            textET.setText(r.toString())
        }

        registerForContextMenu(textET)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_color_quality -> {
                val i = textET.text.toString()
                if (i.isEmpty()) return true
                val num = i.toInt()

//                when (num) {
//                    1 -> {
//                        textET.setBackgroundColor(Color.rgb(255, 165, 0))
//                    }
//
//                    2 -> {
//                        textET.setBackgroundColor(Color.YELLOW)
//                    }
//
//                    3 -> {
//                        textET.setBackgroundColor(Color.GREEN)
//                    }
//
//                    4 -> {
//                        textET.setBackgroundColor(Color.BLUE)
//                    }
//
//                    5 -> {
//                        textET.setBackgroundColor(Color.RED)
//                    }
//                }
                when (num) {
                    in 1..10 -> {
                        textET.setBackgroundColor(Color.RED)
                    }

                    in 11..20 -> {
                        textET.setBackgroundColor(Color.rgb(255, 165, 0))
                    }

                    in 21..30 -> {
                        textET.setBackgroundColor(Color.YELLOW)
                    }

                    in 31..40 -> {
                        textET.setBackgroundColor(Color.GREEN)
                    }

                    in 41..50 -> {
                        textET.setBackgroundColor(Color.BLUE)
                    }
                }
            }

            R.id.menu_exit -> {
                finish()
            }

            else -> return super.onContextItemSelected(item)
        }
        return true
    }


}