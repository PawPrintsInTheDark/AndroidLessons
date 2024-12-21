package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FirstFragment : Fragment() {
    private var notes = mutableListOf<Note>()
    private lateinit var adapter: NoteAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        val dateTV = view.findViewById<EditText>(R.id.dateTV)
        val updateBTN = view.findViewById<Button>(R.id.updateBTN)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRV)

        adapter = NoteAdapter(notes)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : NoteAdapter.OnItemClickListener {
            override fun onItemClick(note: Note, position: Int) {
                val detailsFragment = DetailsFragment.newInstance(note)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })

        updateBTN.setOnClickListener {
            val text = dateTV.text.toString()
            val currentDateTime = SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault()).format(Date())
            if (text.isNotEmpty()) {
                notes.add(Note.create(text, currentDateTime))
                adapter.notifyItemInserted(notes.size - 1)
                dateTV.text.clear()
            }
        }
        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireActivity().finishAffinity()
        return super.onOptionsItemSelected(item)
    }


}
