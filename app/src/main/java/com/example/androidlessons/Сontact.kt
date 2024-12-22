package com.example.androidlessons

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contact(
    @ColumnInfo(name = "surname") var surname: String,
    @ColumnInfo(name = "phone") var phone: String,
    @ColumnInfo(name = "timestamp") var timesTamp: String,
){
    @PrimaryKey(autoGenerate = true)
     var id = 0
}
