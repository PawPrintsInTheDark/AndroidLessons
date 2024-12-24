package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var fab: FloatingActionButton
    private val productList = mutableListOf(
        Product("Яблоко", 100.0, R.drawable.apple),
        Product("Мясо", 700.0, R.drawable.meat),
        Product("Апельсин", 200.0, R.drawable.orange),
        Product("Яблоко", 100.0, R.drawable.apple),
        Product("Мясо", 700.0, R.drawable.meat),
        Product("Апельсин", 200.0, R.drawable.orange),
        Product("Яблоко", 100.0, R.drawable.apple),
        Product("Мясо", 700.0, R.drawable.meat),
        Product("Апельсин", 200.0, R.drawable.orange)
        )
    private val cartList = mutableListOf<Product>()
    private lateinit var layout: RelativeLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val toolbar: Toolbar = findViewById(R.id.toolbarPL)
        fab = findViewById(R.id.fab)
        recyclerView = findViewById(R.id.recyclerView)

        layout = findViewById(R.id.productListLayout)
        BGAnimation.backgroundAnimation(layout)

        setSupportActionBar(toolbar)

        recyclerView.layoutManager = LinearLayoutManager(this)

        productAdapter = ProductAdapter(productList) { product -> onProductClick(product) }
        recyclerView.adapter = productAdapter


        fab.setOnClickListener {

            fab.animate()
                .scaleX(0.6f)
                .scaleY(0.6f)
                .setDuration(200)
                .withEndAction {
                    fab.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(200)
                        .start()
                    val intent = Intent(this, CartActivity::class.java)
                    intent.putExtra("cartList", ArrayList(cartList))
                    startActivity(intent)
                }
                .start()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onProductClick(product: Product) {
        AlertDialog.Builder(this)
            .setTitle(product.name)
            .setMessage("Добавить в корзину?")
            .setPositiveButton("В корзину") { _, _ ->
                cartList.add(product)
                productList.remove(product)
                productAdapter.notifyDataSetChanged()

                recyclerView.animate()
                    .scaleX(1.1f)
                    .scaleY(1.1f)
                    .setDuration(300)
                    .withEndAction {
                        recyclerView.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(300)
                            .start()
                    }
                    .start()
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAffinity()
        return true
    }
}