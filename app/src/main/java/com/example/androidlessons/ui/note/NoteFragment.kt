package com.example.androidlessons.ui.note

import NoteAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlessons.NoteViewModel
import com.example.androidlessons.databinding.FragmentNoteBinding
import com.example.androidlessons.models.Note

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        // Инициализация RecyclerView
        noteAdapter = NoteAdapter(mutableListOf(), { note ->
            noteViewModel.deleteNote(note)
        }, { note ->
            showEditNoteDialog(note)
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteAdapter
        }

        noteViewModel.notes.observe(viewLifecycleOwner) { notes ->
            noteAdapter.notes.clear()
            noteAdapter.notes.addAll(notes)
            noteAdapter.notifyDataSetChanged()
        }

        binding.addNoteButton.setOnClickListener {
            showAddNoteDialog()
        }

        return binding.root
    }

    private fun showAddNoteDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val input = EditText(requireContext())
        builder.setTitle("Добавить заметку")
        builder.setView(input)
        builder.setPositiveButton("Добавить") { _, _ ->
            val noteText = input.text.toString()
            if (noteText.isNotBlank()) {
                noteViewModel.addNote(Note(noteText))
            }
        }
        builder.setNegativeButton("Отмена", null)
        builder.show()
    }

    private fun showEditNoteDialog(note: Note) {
        val builder = AlertDialog.Builder(requireContext())
        val input = EditText(requireContext())
        input.setText(note.text)
        builder.setTitle("Редактировать заметку")
        builder.setView(input)
        builder.setPositiveButton("Сохранить") { _, _ ->
            val newText = input.text.toString()
            if (newText.isNotBlank()) {
                noteViewModel.editNote(note, Note(newText, note.isCompleted))
            }
        }
        builder.setNegativeButton("Отмена", null)
        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}