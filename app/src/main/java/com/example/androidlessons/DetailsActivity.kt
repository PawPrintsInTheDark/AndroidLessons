package com.example.androidlessons

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {

    private lateinit var nameET: TextView
    private lateinit var costET: TextView
    private lateinit var decription: TextView
    private lateinit var imgIV: ImageView

    private lateinit var toolbarMain: androidx.appcompat.widget.Toolbar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Supermarket"

        nameET = findViewById(R.id.product_name)
        costET = findViewById(R.id.product_price)
        decription = findViewById(R.id.product_description)
        imgIV = findViewById(R.id.product_image)

        val product = intent.getSerializableExtra("product") as? Product
        product?.let {
            nameET.text = it.name
            costET.text = it.cost
            decription.text = it.description
            imgIV.setImageURI(Uri.parse(it.image))
        } ?: run {
            nameET.text = "Название не указано"
            costET.text = "Цена не указана"
            decription.text = "Описания нету"
            imgIV.setImageResource(R.drawable.ic_image)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}
