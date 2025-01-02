package com.example.androidlessons.model

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieResponse
}

data class MovieResponse(
    val page: Int,
    val results: List<Movie>
)