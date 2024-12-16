package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ListAdapter(
    context: Context, productList: MutableList<Product>
) : ArrayAdapter<Product>(context, R.layout.list_item, productList) {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val product = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val id = view?.findViewById<TextView>(R.id.idListTV)
        val name = view?.findViewById<TextView>(R.id.nameListTV)
        val weight = view?.findViewById<TextView>(R.id.weightListTV)
        val cost = view?.findViewById<TextView>(R.id.costListTV)

        name?.text = "Наименование:\n" + product?.name
        weight?.text = "Вес: " + product?.weight + "кг."
        cost?.text = "Цена: " + product?.cost + " р."
        id?.text = "Id: " + product?.id.toString()

        return view!!
    }

}