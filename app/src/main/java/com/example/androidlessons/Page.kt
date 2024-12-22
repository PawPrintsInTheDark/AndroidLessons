package com.example.androidlessons

import java.io.Serializable

class Page(
    val name: String,
    val url: String
):Serializable {
    companion object{
        val pages = mutableListOf(
            Page("Новости", "https://edition.cnn.com/"),
            Page("Музыка", "https://tobyfox.bandcamp.com/album/deltarune-chapter-2-ost"),
            Page("Кино", "https://rutube.ru/feeds/movies/moviesgenres/")
        )
    }
}