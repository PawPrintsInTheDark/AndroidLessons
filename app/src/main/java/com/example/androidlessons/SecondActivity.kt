package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {
    private val clothingItems = mutableListOf(
        ClothingItem(R.drawable.item1, "Футболка", "Удобная хлопковая футболка"),
        ClothingItem(R.drawable.item2, "Джинсы", "Стильные джинсы скинни"),
        ClothingItem(R.drawable.item3, "Куртка", "Теплая зимняя куртка"),
        ClothingItem(R.drawable.item4, "Платье", "Легкое летнее платье"),
        ClothingItem(R.drawable.item5, "Шорты", "Комфортные шорты для отдыха"),
        ClothingItem(R.drawable.item6, "Свитер", "Мягкий свитер из шерсти"),
        ClothingItem(R.drawable.item7, "Кроссовки", "Удобные кроссовки для спорта"),
        ClothingItem(R.drawable.item8, "Рубашка", "Классическая рубашка с длинным рукавом"),
        ClothingItem(R.drawable.item9, "Юбка", "Стильная юбка до колена"),
        ClothingItem(R.drawable.item10, "Ботинки", "Модные ботинки на шнурках"),
        ClothingItem(R.drawable.item1, "Футболка", "Удобная хлопковая футболка"),
        ClothingItem(R.drawable.item1, "Футболка", "Удобная хлопковая футболка"),
        ClothingItem(R.drawable.item13, "Пальто", "Элегантное пальто для осени"),
        ClothingItem(R.drawable.item1, "Футболка", "Удобная хлопковая футболка"),
        ClothingItem(R.drawable.item15, "Шарф", "Теплый шарф для зимы"),
        ClothingItem(R.drawable.item16, "Перчатки", "Уютные перчатки для холодной погоды"),
        ClothingItem(R.drawable.item17, "Брюки", "Классические брюки для офиса"),
        ClothingItem(R.drawable.item18, "Сандалии", "Летние сандалии для пляжа"),
        ClothingItem(R.drawable.item1, "Футболка", "Удобная хлопковая футболка"),
        ClothingItem(R.drawable.item20, "Носки", "Удобные носки для повседневной носки")
    )

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerViewRV: RecyclerView

    companion object {
        private const val REQUEST_CODE = 1
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        toolbar = findViewById(R.id.toolbar)
        recyclerViewRV = findViewById(R.id.recyclerViewLV)
        recyclerViewRV.layoutManager = LinearLayoutManager(this)
        recyclerViewRV.setHasFixedSize(true)


        title = "Мой гардероб"
        setSupportActionBar(toolbar)

        val adapter = CustomAdapter(clothingItems)
        recyclerViewRV.adapter = adapter
        adapter.setOnClothesClickListener(object :
            CustomAdapter.OnClothesClickListener {
            override fun onClothingClick(clothingItem: ClothingItem, position: Int) {
                val intent = Intent(this@SecondActivity, DetailsActivity::class.java)
                intent.putExtra("clothes", clothingItem)
                intent.putExtra("pos", position)
                startActivityForResult(intent, REQUEST_CODE)

            }

        })
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val updatedClothes = data?.getSerializableExtra("updatedClothes") as? ClothingItem
            val position = data?.extras?.getInt("position")
            if (updatedClothes != null && position != null && position != -1) {
                clothingItems[position] = updatedClothes
                recyclerViewRV.adapter?.notifyItemChanged(position)
            }
        }


    }


    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAffinity()
        return super.onOptionsItemSelected(item)
    }
}