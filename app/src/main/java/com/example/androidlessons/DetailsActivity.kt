package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout

class DetailsActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var displayImgIV: ImageView
    private lateinit var displayNameTV: TextView
    private lateinit var displayDescriptionTV: TextView
    private lateinit var layoutCL: ConstraintLayout

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        init()

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        var clothes: ClothingItem? = null
        var pos: Int? = null
        if (intent.hasExtra("clothes")&& intent.hasExtra("pos")) {
            clothes = intent.getSerializableExtra("clothes") as ClothingItem
            pos = intent.extras?.getInt("pos")
        }
        if (clothes != null) {
            displayImgIV.setImageResource(clothes.img)
            displayNameTV.text = clothes.name
            displayDescriptionTV.text = clothes.description
        }

        layoutCL.setOnLongClickListener {
                val dialog = AlertDialog.Builder(this)
                val dialogView = layoutInflater.inflate(R.layout.update_dialog, null)
                dialog.setView(dialogView)
                val name = dialogView.findViewById<TextView>(R.id.updateNameET)
                val description = dialogView.findViewById<TextView>(R.id.updateDescriptionET)


                dialog.setTitle("Обновить запись")
                dialog.setMessage("Введите данные ниже:")
                dialog.setPositiveButton("Обновить"){_,_, ->
                    displayNameTV.text = name.text.toString()
                    displayDescriptionTV.text = description.text.toString()
                    val updatedClothes = ClothingItem(clothes!!.img,displayNameTV.text.toString(), description.text.toString())

                    val resultIntent = Intent()
                    resultIntent.putExtra("updatedClothes", updatedClothes)
                    resultIntent.putExtra("position", pos)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
                dialog.setNegativeButton("Отмена"){_,_,->}
                dialog.create().show()
                false
            }

    }

    private fun init() {
        toolbar = findViewById(R.id.toolbarDetails)
        displayImgIV = findViewById(R.id.displayImgIV)
        displayNameTV = findViewById(R.id.displayNameTV)
        displayDescriptionTV = findViewById(R.id.displayDescriptionTV)
        layoutCL = findViewById(R.id.layoutCL)
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAffinity()
        return super.onOptionsItemSelected(item)
    }
}