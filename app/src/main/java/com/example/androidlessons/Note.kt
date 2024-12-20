package com.example.androidlessons

class Note(
    val id : Int,
    val text: String,
    val dateTimeCreated: String,
    var isChecked: Boolean = false
){
    companion object{
        private var id = 0
        fun create(text: String,dateTimeCreated: String): Note {
            id++
            return Note(id,text,dateTimeCreated)
        }
    }
}