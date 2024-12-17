package com.example.androidlessons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val clothes : MutableList<ClothingItem>) : RecyclerView.Adapter<CustomAdapter.ClothesViewHolder>(){

    class ClothesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val img : ImageView = itemView.findViewById(R.id.imgListItemIV)
        val name : TextView = itemView.findViewById(R.id.nameListItemTV)
        val description : TextView = itemView.findViewById(R.id.descriptionListItemTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item,parent,false)
        return ClothesViewHolder(itemView)
    }

    override fun getItemCount() = clothes.size

    override fun onBindViewHolder(holder: ClothesViewHolder, position: Int) {
        val item = clothes[position]
        holder.img.setImageResource(item.img)
        holder.name.text = item.name
        holder.description.text = item.description


    }
}
