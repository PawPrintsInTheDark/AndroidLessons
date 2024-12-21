package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ViewPagerAdapter(this, OnBoardingFragmentViewPagerModel.viewPagerList)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter

    }


}