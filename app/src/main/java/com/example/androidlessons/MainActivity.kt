package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val db = DBHelper(this, null)

    private var products = mutableListOf<Product>()
    private var listAdapter: ListAdapter? = null

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var saveBTN: Button
    private lateinit var updateBTN: Button
    private lateinit var deleteBTN: Button
    private lateinit var nameET: EditText
    private lateinit var weightET: EditText
    private lateinit var costET: EditText
    private lateinit var listViewLV: ListView

    @SuppressLint("MissingInflatedId", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        saveBTN.setOnClickListener {
            saveRecords()
        }

    }

    override fun onResume() {
        super.onResume()
        updateBTN.setOnClickListener {
            updateRecord()
        }
        deleteBTN.setOnClickListener {
            deleteRecord()
        }
    }



    private fun viewDataAdapter() {
        products = db.readProducts()
        listAdapter = ListAdapter(this, products)
        listViewLV.adapter = listAdapter
        listAdapter?.notifyDataSetChanged()
    }

    private fun saveRecords() {
        val name = nameET.text.toString()
        val weight = weightET.text.toString()
        val cost = costET.text.toString()
        if (name.trim() != "" && weight.trim() != "" && cost.trim() != "") {
            val product = Product(name, weight, cost)
            products.add(product)
            db.addProduct(product)
            Toast.makeText(this, "Запись добавлена", Toast.LENGTH_SHORT).show()
            clearFields()
            viewDataAdapter()
        }

    }

    private fun clearFields() {
        nameET.text.clear()
        weightET.text.clear()
        costET.text.clear()
    }

    private fun init() {
        toolbar = findViewById(R.id.toolbarMain)
        saveBTN = findViewById(R.id.saveBTN)
        updateBTN = findViewById(R.id.updateBTN)
        deleteBTN = findViewById(R.id.deleteBTN)
        nameET = findViewById(R.id.enterNameET)
        weightET = findViewById(R.id.enterWeightET)
        costET = findViewById(R.id.enterCostET)
        listViewLV = findViewById(R.id.listViewLV)

        toolbar.title = "SQLite"
        setSupportActionBar(toolbar)
        viewDataAdapter()
    }

    private fun deleteRecord() {
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)
        val chooseDeleteId = dialogView.findViewById<EditText>(R.id.deleteIdET)

        dialogBuilder.setTitle("Удалить запись")
        dialogBuilder.setMessage("Введите индетификатор:")
        dialogBuilder.setPositiveButton("Удалить") { _, _ ->
            val deleteId = chooseDeleteId.text.toString()
            if (deleteId.trim() != "") {
                val product = Product("", "", "", Integer.parseInt(deleteId))
                db.removeProduct(product)
                viewDataAdapter()
                Toast.makeText(this, "Запись удалена", Toast.LENGTH_SHORT).show()
            }
        }
        dialogBuilder.setNegativeButton("Отмена") { _, _ -> }
        dialogBuilder.create().show()

    }

    private fun updateRecord() {
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)
        val editId = dialogView.findViewById<EditText>(R.id.updateIdET)
        val editName = dialogView.findViewById<EditText>(R.id.updatNameET)
        val editWeight = dialogView.findViewById<EditText>(R.id.updateWeightET)
        val editCost = dialogView.findViewById<EditText>(R.id.updateCostET)

        dialogBuilder.setTitle("Обновить запись")
        dialogBuilder.setMessage("Введите данные ниже:")
        dialogBuilder.setPositiveButton("Обновить") { _, _ ->
            val updateId = editId.text.toString()
            val updateName = editName.text.toString()
            val updateWeight = editWeight.text.toString()
            val updateCost = editCost.text.toString()
            if (updateId.trim() != "" && updateName.trim() != "" && updateCost.trim() != "" && updateWeight.trim() != "") {
                val product = Product(updateName, updateWeight, updateCost, updateId.toInt())
                db.updateProduct(product)
                viewDataAdapter()
                Toast.makeText(this, "Запись обновлена", Toast.LENGTH_SHORT).show()
            }
        }
        dialogBuilder.setNegativeButton("Отмена") { dialog, which -> }
        dialogBuilder.create().show()
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