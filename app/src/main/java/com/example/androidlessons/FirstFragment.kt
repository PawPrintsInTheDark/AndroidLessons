package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FirstFragment : Fragment() {
    private var notes = mutableListOf<Note>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        super.onViewCreated(view, savedInstanceState)
        val dateTV = view.findViewById<EditText>(R.id.dateTV)
        val updateBTN = view.findViewById<Button>(R.id.updateBTN)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRV)

        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NoteAdapter(notes)
        recyclerView.adapter = adapter



        updateBTN.setOnClickListener {
            val text = dateTV.text.toString()
            val currentDateTime = SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault()).format(
                Date()
            )
            if (text.isNotEmpty()) {
                notes.add(Note.create(text, currentDateTime))
                adapter.notifyDataSetChanged()
            }
            dateTV.text.clear()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        activity?.finishAffinity()
        return super.onOptionsItemSelected(item)
    }

}
