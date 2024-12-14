package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val db = DBHelper(this, null)

    private var products = mutableListOf<Product>()
    private var listAdapter: ListAdapter? = null

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var addBTN: Button
    private lateinit var nameET: EditText
    private lateinit var weightET: EditText
    private lateinit var costET: EditText
    private lateinit var listViewLV: ListView

    @SuppressLint("MissingInflatedId", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        addBTN.setOnClickListener {
            products.clear()
            listAdapter = ListAdapter(this@MainActivity, products)
            listViewLV.adapter = listAdapter

            var name = nameET.text.toString()
            var weight = weightET.text.toString()
            var cost = costET.text.toString()

            db.addName(name, weight, cost)
            Toast.makeText(
                this,
                "$name, $weight, $cost добавлены в базу данных",
                Toast.LENGTH_SHORT
            ).show()
            clearFields()

            val cursor = db.getInfo()
            if (cursor != null && cursor.moveToFirst()) {
                cursor.moveToFirst()
                name = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME))
                weight = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_WEIGHT))
                cost = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_COST))
                createProduct(name, weight, cost)
            }
            while (cursor!!.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME))
                weight = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_WEIGHT))
                cost = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_COST))
                createProduct(name, weight, cost) }
            cursor.close()

            listAdapter!!.notifyDataSetChanged()
        }

        listViewLV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                products.clear()
                db.removeALL()
                listAdapter?.notifyDataSetChanged()
            }


    }

    private fun createProduct(name: String, weight: String, cost: String) {
        products.add(Product(name, weight, cost))
    }

    private fun clearFields() {
        nameET.text.clear()
        weightET.text.clear()
        costET.text.clear()
    }

    private fun init() {
        toolbar = findViewById(R.id.toolbarMain)
        addBTN = findViewById(R.id.addBTN)
        nameET = findViewById(R.id.enterNameET)
        weightET = findViewById(R.id.enterWeightET)
        costET = findViewById(R.id.enterCostET)
        listViewLV = findViewById(R.id.listViewLV)

        toolbar.title = "SQLite"
        setSupportActionBar(toolbar)
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