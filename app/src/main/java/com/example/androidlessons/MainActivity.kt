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
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
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

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // Код для смены цвета текста у ListView
        val adapter =
            object :
                ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, mutableListOf()) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    val tv = view.findViewById<View>(android.R.id.text1) as TextView
                    tv.setTextColor(Color.BLACK)
                    return view
                }
            }
        listviewLV.adapter = adapter

        userViewModel.currentUsers.observe(this) {
            adapter.clear()
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
        }


        saveBTN.setOnClickListener {
            val name = nameET.text.toString()
            val age = ageET.text.toString()
            if (name.isNotEmpty() && age.isNotEmpty()) {
                userViewModel.addUser(User(name, age.toInt()))
                nameET.text.clear()
                ageET.text.clear()

            }
        }

        listviewLV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                Toast.makeText(
                    this,
                    "Пользователь \"${userViewModel.currentUsers.value?.get(position)?.name}\" удалён",
                    Toast.LENGTH_SHORT
                ).show()
                userViewModel.removeUser(position)
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