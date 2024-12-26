package com.example.androidlessons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidlessons.models.Note

class NoteViewModel : ViewModel() {
    private val _notes = MutableLiveData<MutableList<Note>>(mutableListOf())
    val notes: LiveData<MutableList<Note>> get() = _notes

    fun addNote(note: Note) {
        _notes.value?.add(note)
        _notes.value = _notes.value
    }

    fun deleteNote(note: Note) {
        _notes.value?.remove(note)
        _notes.value = _notes.value
    }

    fun editNote(oldNote: Note, newNote: Note) {
        val index = _notes.value?.indexOf(oldNote)
        if (index != null && index >= 0) {
            _notes.value?.set(index, newNote)
            _notes.value = _notes.value
        }
    }
}
