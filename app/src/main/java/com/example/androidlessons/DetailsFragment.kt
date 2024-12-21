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
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment


class DetailsFragment : Fragment() {

    private lateinit var note: Note

    companion object {
        private const val ARG_NOTE = "note"

        fun newInstance(note: Note): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putSerializable(ARG_NOTE, note)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            note = it.getSerializable(ARG_NOTE) as Note
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbarDetails)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)


        val editNoteET = view.findViewById<TextView>(R.id.editNoteET)
        val editBTN = view.findViewById<Button>(R.id.editBTN)

        editNoteET.text = note.text

        editBTN.setOnClickListener{
            note.text = editNoteET.text.toString()
            requireActivity().supportFragmentManager.popBackStack()
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