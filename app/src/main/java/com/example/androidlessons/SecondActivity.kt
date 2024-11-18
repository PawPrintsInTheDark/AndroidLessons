package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private var products : MutableList<Product> = mutableListOf()

    private lateinit var listviewLV: ListView
    private lateinit var addBTN: Button
    private lateinit var nameET: EditText
    private lateinit var costET: EditText
    private lateinit var imgIV: ImageView
    private val GALLERY_REQUEST = 290
    private var selectedImg : Uri? = null


    private lateinit var toolbarMain: androidx.appcompat.widget.Toolbar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        init()
        setSupportActionBar(toolbarMain)
        title = "Supermarket"


        imgIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }

        addBTN.setOnClickListener{
            createProduct()

            val listAdapter = ListAdapter(this@SecondActivity, products)
            listviewLV.adapter = listAdapter
            listAdapter.notifyDataSetChanged()

            resetEditFields()
        }
    }

    private fun createProduct() {
        val productName = nameET.text.toString()
        val productCost = costET.text.toString() + " руб."
        val productImg = selectedImg
        val product = Product(productName, productCost, productImg)
        products.add(product)
    }

    private fun resetEditFields() {
        nameET.text.clear()
        costET.text.clear()
        imgIV.setImageResource(R.drawable.ic_image)
    }

    private fun init() {
        addBTN = findViewById(R.id.addBTN)
        nameET = findViewById(R.id.nameET)
        costET = findViewById(R.id.costET)
        listviewLV = findViewById(R.id.listViewLV)
        imgIV = findViewById(R.id.editImageIV)
        toolbarMain = findViewById(R.id.toolbarMain)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imgIV = findViewById(R.id.editImageIV)
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            selectedImg = data?.data
            imgIV.setImageURI(selectedImg)
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