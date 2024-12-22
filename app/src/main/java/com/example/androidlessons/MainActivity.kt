package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {
    var db: ContactDatabase? = null
    private lateinit var toolbar: Toolbar
    private lateinit var surnameET: EditText
    private lateinit var phoneET: EditText
    private lateinit var saveBTN: Button
    private lateinit var textView: TextView

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbar)

        surnameET = findViewById(R.id.surnameET)
        phoneET = findViewById(R.id.phoneET)
        saveBTN = findViewById(R.id.saveBTN)
        textView = findViewById(R.id.textView)

        db = ContactDatabase.getDatabase(this)
        readDatabase()
    }

    override fun onResume() {
        super.onResume()
        saveBTN.setOnClickListener {
            val contact = Contact(surnameET.text.toString(), phoneET.text.toString(), Date().time.toString())
            addContact(contact)
            readDatabase()
//            lifecycleScope.launch{
//                db?.getContactDao()?.deleteAll()
//            }
            surnameET.text.clear()
            phoneET.text.clear()
        }
    }

    private fun addContact(contact: Contact) {
        lifecycleScope.launch {
            db?.getContactDao()?.insert(contact)
        }
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            textView.text = ""
            val list = db?.getContactDao()?.getAllContacts() ?: emptyList()
            list.forEach { i ->
                textView.append("Фамилия: ${i.surname}, Тел. ${i.phone}\n")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAffinity()
        return super.onOptionsItemSelected(item)
    }
}
