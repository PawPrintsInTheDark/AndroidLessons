package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.SimpleTimeZone
import java.util.TimeZone

class MainActivity : AppCompatActivity() , ContactAdapter.ContactClickListener {
    private lateinit var viewModel: ContactViewModel

    private lateinit var toolbar: Toolbar
    private lateinit var surnameET: EditText
    private lateinit var phoneET: EditText
    private lateinit var saveBTN: Button
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbar)

        surnameET = findViewById(R.id.surnameET)
        phoneET = findViewById(R.id.phoneET)
        saveBTN = findViewById(R.id.saveBTN)
        recyclerView = findViewById(R.id.recyclerView)


        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ContactAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application))[ContactViewModel::class.java]
        viewModel.contacts.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })

    }

    override fun onItemClicked(contact: Contact) {
        viewModel.deleteContact(contact)
        Toast.makeText(this, "Контакт ${contact.surname} удалён.", Toast.LENGTH_SHORT).show()
    }

    fun saveData(view:View){
        val contactSurname = surnameET.text.toString()
        val  contactPhone = phoneET.text.toString()
        val timestamp = formatMilliseconds(Date().time)
        if (contactPhone.isNotEmpty() || contactSurname.isNotEmpty()){
            viewModel.insertContact(Contact(contactSurname,contactPhone, timestamp))
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show()
            surnameET.text.clear()
            phoneET.text.clear()
        }
    }

    private fun formatMilliseconds(time: Long): String {
        val timeFormat = SimpleDateFormat("EEE, HH:mm")
        timeFormat.timeZone = TimeZone.getTimeZone("GMT+04")
        return timeFormat.format(Date(time))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAffinity()
        return true
    }
}
