package com.example.androidlessons

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val userList: MutableList<User> = mutableListOf<User>()
    private lateinit var saveBTN: Button
    private lateinit var nameET: EditText
    private lateinit var ageET: EditText
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
        listviewLV = findViewById(R.id.listViewLV)

        // Код для смены цвета текста у ListView
        val adapter =
            object : ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, userList) {
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
            if (name.isNotEmpty() && age.isNotEmpty()) {
                userList.add(User(name, age.toInt()))
                adapter.notifyDataSetChanged()
                nameET.text.clear()
                ageET.text.clear()
            }
        }

        listviewLV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                Toast.makeText(this, "Пользователь \"${userList[position].name}\" удалён", Toast.LENGTH_SHORT).show()
                userList.removeAt(position)
                adapter.notifyDataSetChanged()
            }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


}