package com.example.androidlessons

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
import androidx.appcompat.app.AppCompatActivity

@Suppress(" CAST_NEVER_SUCCEEDS")
class MainActivity : AppCompatActivity() {

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

    private var persons: MutableList<Person> = mutableListOf()
    private var listAdapter: ListAdapter? = null


    private lateinit var postSpinner: Spinner
    private lateinit var ageET: EditText
    private lateinit var nameET: EditText
    private lateinit var secondNameET: EditText
    private lateinit var addBTN: Button
    private lateinit var listViewLV: ListView

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    var post: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        toolbar.title = "Подбор персонала"
        setSupportActionBar(toolbar)

        configureSpinner()

        addBTN.setOnClickListener {
            createProduct()
            listAdapter = ListAdapter(this@MainActivity, persons)
            listViewLV.adapter = listAdapter
            listAdapter!!.notifyDataSetChanged()

            resetEditFields()

        }

        listViewLV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
//                val product = listViewLV.adapter.getItem(position) as? Person
                persons.removeAt(position)
                listAdapter?.notifyDataSetChanged()
            }

    }

    private fun init() {
        toolbar = findViewById(R.id.toolbarMain)
        postSpinner = findViewById(R.id.postSpinner)
        ageET = findViewById(R.id.ageET)
        nameET = findViewById(R.id.nameET)
        secondNameET = findViewById(R.id.secondNameET)
        addBTN = findViewById(R.id.addBTN)
        listViewLV = findViewById(R.id.listViewLV)
    }

    private fun resetEditFields() {
        nameET.text.clear()
        secondNameET.text.clear()
        ageET.text.clear()
        postSpinner.setSelection(0)
    }

    private fun createProduct() {
        persons.add(
            Person(
                nameET.text.toString(),
                secondNameET.text.toString(),
                ageET.text.toString(),
                post
            )
        )
    }

    private fun configureSpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            role
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        postSpinner.adapter = adapter

        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    val item = parent?.getItemAtPosition(position) as String
                    post = item

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        postSpinner.onItemSelectedListener = itemSelectedListener
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