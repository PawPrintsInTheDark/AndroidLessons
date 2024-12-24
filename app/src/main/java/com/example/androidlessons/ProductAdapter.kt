package com.example.androidlessons

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class ProductAdapter(
    private val productList: List<Product>,
    private val onProductClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage: ImageView = itemView.findViewById(R.id.productImage)
        private val productName: TextView = itemView.findViewById(R.id.productName)
        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)


        @SuppressLint("SetTextI18n")
        fun bind(product: Product) {
            productImage.setImageResource(product.imageResId)
            productName.text = product.name
            productPrice.text = "${product.price} ₽"

            itemView.setOnClickListener { onProductClick(product) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.itemView.alpha = 0f
        holder.itemView.visibility = View.VISIBLE

        holder.itemView.animate()
            .alpha(1f)
            .setDuration(500)
            .setStartDelay((position * 200).toLong())
            .start()

        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
