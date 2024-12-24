    package com.example.androidlessons

    import android.os.Bundle
    import android.view.Menu
    import android.view.MenuItem
    import android.widget.LinearLayout
    import android.widget.TextView
    import androidx.appcompat.app.AppCompatActivity
    import androidx.appcompat.widget.Toolbar

    class ReceiptActivity : AppCompatActivity() {
        private lateinit var receiptTextView: TextView
        private val receiptList = mutableListOf<Product>()
        private lateinit var layout: LinearLayout

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_receipt)

            val toolbar: Toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)

            layout = findViewById(R.id.receiptLayout)
            BGAnimation.backgroundAnimation(layout)


            receiptTextView = findViewById(R.id.receiptTextView)

            val receivedList = intent.getSerializableExtra("receiptList") as? ArrayList<Product>
            if (receivedList != null) {
                receiptList.addAll(receivedList)
            }

            val receiptText = StringBuilder("Чек:\n\n")
            var totalAmount = 0.0

            for (product in receiptList) {
                receiptText.append("${product.name} - ${product.price} ₽\n")
                totalAmount += product.price
            }

            // Если корзина пуста, выводим сообщение
            if (receiptList.isEmpty()) {
                receiptText.append("Корзина пуста.")
            } else {
                receiptText.append("\nОбщая сумма: ${totalAmount} ₽")
            }

            receiptTextView.text = receiptText.toString()
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
