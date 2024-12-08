package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {

    private lateinit var nameET: EditText
    private lateinit var costET: EditText
    private lateinit var descriptionET: EditText
    private lateinit var imgIV: ImageView
    private lateinit var saveBTN: Button

    private val GALLERY_REQUEST = 230
    private var selectedImg: Uri? = null

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
        descriptionET = findViewById(R.id.product_description)
        imgIV = findViewById(R.id.product_image)
        saveBTN = findViewById(R.id.save_product)

        imgIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }

        val product = intent.extras?.getSerializable("product") as Product
        val products = intent.getSerializableExtra("products")
        val item = intent.extras?.getInt("item")
        var check = intent.extras?.getBoolean("check")
        nameET.setText(product.name)
        costET.setText(product.cost)
        descriptionET.setText(product.description)
        imgIV.setImageURI(Uri.parse(product.image))

        saveBTN.setOnClickListener {
            val product = Product(
                nameET.text.toString(),
                costET.text.toString(),
                product.image,
                descriptionET.text.toString()
            )
            val list = products as MutableList<Product>
            if (item != null) {
                swap(item, product, products)
            }
            check = false
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("list", list as ArrayList<Product>)
            intent.putExtra("newCheck", check)
            startActivity(intent)
            finish()

        }

    }

    private fun swap(item: Int, product: Product, products: MutableList<Product>) {
        products.add(item + 1, product)
        products.removeAt(item)

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imgIV = findViewById(R.id.product_image)
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            selectedImg = data?.data
            imgIV.setImageURI(selectedImg)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> {
                Toast.makeText(this, "Программа завершена", Toast.LENGTH_SHORT).show()
                finishAffinity()
            }

            R.id.backMenuMain -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
