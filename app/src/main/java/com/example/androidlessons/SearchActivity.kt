package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlessons.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var customAdapter: CustomAdapter
    private var contactModelList: MutableList<ContactModel> = mutableListOf()
    private var filteredContactList: MutableList<ContactModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        backgroundAnimation()
        contactModelList = intent.getSerializableExtra("CONTACT_LIST") as ArrayList<ContactModel>

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.searchButton.setOnClickListener {
            val query = binding.searchInput.text.toString()
            if (query.isNotEmpty()) {
                searchContacts(query)
            } else {
                Toast.makeText(this, "Введите символы для поиска", Toast.LENGTH_SHORT).show()
            }
        }

        customAdapter = CustomAdapter(this, filteredContactList,
            onCallClick = { number -> callTheNumber(number) },
            onMessageClick = { number -> openMessageActivity(number) }
        )
        binding.recyclerView.adapter = customAdapter
    }

    private fun backgroundAnimation() {
        val animation: AnimationDrawable = binding.searchLayout.background as AnimationDrawable
        animation.apply {
            setEnterFadeDuration(500)
            setExitFadeDuration(1500)
            start()
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun searchContacts(query: String) {
        filteredContactList.clear()
        for (contact in contactModelList) {
            if (contact.name.contains(query, ignoreCase = true) ||
                contact.phone.contains(query)) {
                filteredContactList.add(contact)
            }
        }
        customAdapter.notifyDataSetChanged()
    }


    private fun callTheNumber(number: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }

    private fun openMessageActivity(phoneNumber: String) {
        val intent = Intent(this, MessageActivity::class.java)
        intent.putExtra("PHONE_NUMBER", phoneNumber)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.exitMenu -> finishAffinity()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
