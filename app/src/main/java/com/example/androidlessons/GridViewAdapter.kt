package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.androidlessons.databinding.GridviewItemBinding

class GridViewAdapter(
    private val list: List<GridViewModal>,
    private val context: Context
) :
    BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private lateinit var textViewTV: TextView
    private lateinit var imageViewIV: ImageView
    override fun getCount() = list.size

    override fun getItem(position: Int): Any? {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: GridviewItemBinding
        if (convertView == null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = GridviewItemBinding.inflate(layoutInflater!!, parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as GridviewItemBinding
        }

        binding.imageViewIV.setImageResource(list[position].img)
        binding.textViewTV.text = list[position].name

        return binding.root

    }

}