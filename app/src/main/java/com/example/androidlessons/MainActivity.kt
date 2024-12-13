package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

@Suppress(" CAST_NEVER_SUCCEEDS")
class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private lateinit var gridViewMainGV: GridView
    private var list = mutableListOf(
        GridViewModal("Youtube", R.drawable.youtube_icon, "https://www.youtube.com"),
        GridViewModal("Instagram", R.drawable.instagram_icon,"https://www.instagram.com"),
        GridViewModal("Google", R.drawable.google_icon,"https://www.google.com"),
        GridViewModal("Яндекс", R.drawable.yandex,"https://www.ya.ru"),
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbarMain)
        gridViewMainGV = findViewById(R.id.gridViewMainGV)

        toolbar.title = "Мобильный браузер"
        setSupportActionBar(toolbar)

        val adapter = GridViewAdapter(list,this@MainActivity)
        gridViewMainGV.adapter = adapter

        gridViewMainGV.onItemClickListener = AdapterView.OnItemClickListener{
            _,_,position,_ ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(list[position].src)))
        }


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