package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar


    private val db = DBHelper(this, null)

    private var persons = mutableListOf<Person>()
    private var listAdapter: ListAdapter? = null

    private lateinit var saveBTN: Button
    private lateinit var getDataBTN: Button
    private lateinit var deleteBTN: Button
    private lateinit var nameET: EditText
    private lateinit var phoneET: EditText
    private lateinit var postSpinner: Spinner
    private lateinit var listViewLV: ListView

    private var role = mutableListOf(
        "Должность",
        "Разработчик",
        "Старший разработчик",
        "Технический лидер",
        "Архитектор ПО",
        "Тестировщик",
        "Аналитик данных",
        "DevOps-инженер",
        "Системный администратор",
        "UI/UX-дизайнер",
        "Продуктовый менеджер",
        "Маркетолог в IT",
        "Специалист по кибербезопасности",
        "Технический писатель",
        "Стажер-программист"
    )
    var post: String = ""


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        init()
        configureSpinner()
        saveBTN.setOnClickListener {
            val name = nameET.text.toString()
            val weight = phoneET.text.toString()

            db.addPerson(name, weight, post)
            Toast.makeText(
                this,
                "$name, $weight, $post добавлены в базу данных",
                Toast.LENGTH_SHORT
            ).show()
            clearFields()
        }

        getDataBTN.setOnClickListener {
            persons.clear()
            listAdapter = ListAdapter(this@SecondActivity, persons)
            listViewLV.adapter = listAdapter

            var name = ""
            var phone = ""

            val cursor = db.getInfo()
            if (cursor != null && cursor.moveToFirst()) {
                cursor.moveToFirst()
                name = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME))
                phone = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PHONE))
                post = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_POST))
                createPerson(name, phone, post)
            }
            while (cursor!!.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME))
                phone = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PHONE))
                post = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_POST))
                createPerson(name, phone, post)
            }
            cursor.close()

            listAdapter?.notifyDataSetChanged()
        }

        deleteBTN.setOnClickListener {
            db.removeALL()
            listAdapter?.clear()
            listAdapter?.notifyDataSetChanged()
        }


    }

    private fun configureSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, role)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        postSpinner.adapter = adapter

        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val item = parent?.getItemAtPosition(position) as String
                    post = item
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        postSpinner.onItemSelectedListener = itemSelectedListener
    }

    private fun createPerson(name: String, phone: String, post: String) {
        persons.add(Person(name, phone, post))
    }

    private fun clearFields() {
        nameET.text.clear()
        phoneET.text.clear()
        postSpinner.setSelection(0)
    }

    private fun init() {
        toolbar = findViewById(R.id.toolbarMain)
        saveBTN = findViewById(R.id.saveBTN)
        getDataBTN = findViewById(R.id.getDataBTN)
        deleteBTN = findViewById(R.id.deleteBTN)
        nameET = findViewById(R.id.nameET)
        postSpinner = findViewById(R.id.postSpinner)
        phoneET = findViewById(R.id.phoneET)
        listViewLV = findViewById(R.id.listViewLV)

        toolbar.title = "База данных"
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