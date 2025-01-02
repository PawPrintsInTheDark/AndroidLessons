package com.example.androidlessons.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidlessons.databinding.ItemFilmBinding
import com.example.androidlessons.model.Movie

class MainAdapter : PagingDataAdapter<Movie, MainAdapter.MainViewHolder>(DIFF_CALLBACK) {

    inner class MainViewHolder(val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = getItem(position)
        holder.binding.apply {
            filmName.text = movie?.title ?: "Unknown Title"
            val posterUrl = "https://image.tmdb.org/t/p/w500${movie?.poster_path}"
            Glide.with(holder.itemView.context)
                .load(posterUrl)
                .into(filmPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}
