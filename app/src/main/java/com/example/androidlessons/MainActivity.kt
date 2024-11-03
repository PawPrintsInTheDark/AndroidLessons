package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var adapter : ArrayAdapter<Person>? = null

    private val personList: MutableList<Person> = mutableListOf<Person>()
    private lateinit var saveBTN: Button
    private lateinit var nameET: EditText
    private lateinit var ageET: EditText
    private lateinit var addressET: EditText
    private lateinit var numberET: EditText
    private lateinit var listviewLV: ListView
    private lateinit var toolbarMain: androidx.appcompat.widget.Toolbar


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Каталог пользователей"

        saveBTN = findViewById(R.id.SaveButton)
        nameET = findViewById(R.id.NametInput)
        ageET = findViewById(R.id.AgeInput)
        addressET = findViewById(R.id.addressInput)
        numberET = findViewById(R.id.numberInput)
        listviewLV = findViewById(R.id.listViewLV)

        // Код для смены цвета текста у ListView
        adapter =
            object : ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, personList) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    val tv = view.findViewById<View>(android.R.id.text1) as TextView
                    tv.setTextColor(Color.BLACK)
                    return view
                }
            }

        listviewLV.adapter = adapter

        saveBTN.setOnClickListener {
            val name = nameET.text.toString()
            val age = ageET.text.toString()
            val address = addressET.text.toString()
            val number = numberET.text.toString()
            if (name.isNotEmpty() && age.isNotEmpty()) {

                personList.add(Person(name, age, address, number))
                adapter!!.notifyDataSetChanged()

                nameET.text.clear()
                ageET.text.clear()
                addressET.text.clear()
                numberET.text.clear()

            }
        }

        listviewLV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("EXTRA_PERSON", personList[position])
                startActivity(intent)
            }
    }




}