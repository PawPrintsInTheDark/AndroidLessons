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

class CartActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: ProductAdapter
    private lateinit var fab: FloatingActionButton
    private val cartList = mutableListOf<Product>()
    private lateinit var layout: RelativeLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        toolbar = findViewById(R.id.toolbarC)
        setSupportActionBar(toolbar)
        layout = findViewById(R.id.cartLayout)
        BGAnimation.backgroundAnimation(layout)



        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val receivedList = intent.getSerializableExtra("cartList") as? ArrayList<Product>
        if (receivedList != null) {
            cartList.addAll(receivedList)
        }

        cartAdapter = ProductAdapter(cartList) { product -> onCartItemClick(product) }
        recyclerView.adapter = cartAdapter

        fab = findViewById(R.id.fab)
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
                    val intent = Intent(this, ReceiptActivity::class.java)
                    intent.putExtra("receiptList", ArrayList(cartList))
                    startActivity(intent)
                }
                .start()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onCartItemClick(product: Product) {
        AlertDialog.Builder(this)
            .setTitle(product.name)
            .setMessage("Удалить из корзины?")
            .setPositiveButton("Удалить") { _, _ ->
                cartList.remove(product)
                cartAdapter.notifyDataSetChanged()
                recyclerView.animate()
                    .scaleX(0.9f)
                    .scaleY(0.9f)
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
