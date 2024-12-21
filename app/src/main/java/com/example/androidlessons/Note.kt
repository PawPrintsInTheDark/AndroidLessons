package com.example.androidlessons

import java.io.Serializable

class Note(
    val id : Int,
    var text: String,
    val dateTimeCreated: String,
    var isChecked: Boolean = false
): Serializable
{
    companion object{
        private var id = 0
        fun create(text: String,dateTimeCreated: String): Note {
            id++
            return Note(id,text,dateTimeCreated)
        }
    }
}