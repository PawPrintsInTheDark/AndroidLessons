package com.example.androidlessons

class ContactData(
    val name: String? = null,
    val phone: String? = null,
) {
    override fun toString(): String {
        return "$name, $phone"
    }
}