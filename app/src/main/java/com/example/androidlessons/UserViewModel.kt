package com.example.androidlessons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    val currentUsers: MutableLiveData<MutableList<User>> by lazy { MutableLiveData<MutableList<User>>() }


    fun addUser(user: User) {
        val currentList = currentUsers.value ?: mutableListOf()
        currentList.add(user)
        currentUsers.value = currentList
    }

    fun removeUser(position: Int) {
        val currentList = currentUsers.value ?: mutableListOf()
        if (position in currentList.indices) {
            currentList.removeAt(position)
            currentUsers.value = currentList
        }
    }

}