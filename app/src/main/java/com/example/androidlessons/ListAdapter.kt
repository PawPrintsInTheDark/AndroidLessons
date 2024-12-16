package com.example.androidlessons

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ListAdapter(
    context: Context, productList: MutableList<Person>
) : ArrayAdapter<Person>(context, R.layout.employee_item, productList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val product = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false)
        }

        val name = view?.findViewById<TextView>(R.id.nameListTV)
        val phone = view?.findViewById<TextView>(R.id.secondNameListTV)
        val post = view?.findViewById<TextView>(R.id.postListTV)

        name?.text = product?.name
        phone?.text = product?.post
        post?.text = product?.phone.toString()

        return view!!
    }

}