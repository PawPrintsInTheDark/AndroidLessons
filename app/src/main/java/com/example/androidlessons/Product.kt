package com.example.androidlessons

import java.io.Serializable

class Product(
    val name: String,
    val cost: String,
    val image: String,
    val description : String
) : Serializable