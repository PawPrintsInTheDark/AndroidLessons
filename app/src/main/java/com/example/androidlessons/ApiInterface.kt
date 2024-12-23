package com.example.androidlessons

import com.example.androidlessons.models.ApiDataCat
import com.example.androidlessons.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("/posts")
    suspend fun createPost(
        @Body user: User
    ): Response<User>

    @FormUrlEncoded
    @POST("/posts")
    suspend fun createUrlPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String,
        ): Response<User>

    @GET("v1/images/search")
    suspend fun getRandomDog(): ApiDataCat
}