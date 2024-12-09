package com.example.androidlessons

import java.io.Serializable
import java.time.LocalDate

data class Person(
    val name: String,
    val secondName: String,
    val birthday: LocalDate,
    val phone: String,
    val image: String
): Serializable