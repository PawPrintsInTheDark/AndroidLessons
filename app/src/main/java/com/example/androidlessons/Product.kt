package com.example.androidlessons

import java.io.Serializable

data class Product(
    val name: String,
    val price: Double,
    val imageResId: Int
): Serializable
