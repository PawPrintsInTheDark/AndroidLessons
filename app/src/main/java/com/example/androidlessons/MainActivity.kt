package com.example.androidlessons

import android.annotation.SuppressLint
import android.app.role.RoleManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val pages  = Page.pages
    private lateinit var adapter: PageAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = PageAdapter(this,pages)
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout,viewPager){tab,position ->
            val name = pages[position].name
            tab.text = name

        }.attach()
    }



}