package com.example.androidlessons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapter(private val currencies: List<Currency>) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.currency_name)
        val image: ImageView = itemView.findViewById(R.id.currency_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencies[position]
        holder.name.text = currency.name
        holder.image.setImageResource(currency.imageResId)
    }

    override fun getItemCount(): Int = currencies.size
}
