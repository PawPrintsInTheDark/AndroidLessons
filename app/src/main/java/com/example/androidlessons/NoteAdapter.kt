package com.example.androidlessons

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val notes: MutableList<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(note: Note, position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noteNumber: TextView = view.findViewById(R.id.noteNumber)
        val noteText: TextView = view.findViewById(R.id.noteText)
        val noteCheckBox: CheckBox = view.findViewById(R.id.noteCheckBox)
        val noteDate: TextView = view.findViewById(R.id.noteDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount() = notes.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.noteNumber.text = note.id.toString() + "."
        holder.noteText.text = note.text
        holder.noteCheckBox.isChecked = note.isChecked
        holder.noteDate.text = note.dateTimeCreated

        holder.noteCheckBox.setOnCheckedChangeListener { _, isChecked ->
            note.isChecked = isChecked
        }
        holder.itemView.setOnClickListener{
            if (onItemClickListener != null){
                onItemClickListener!!.onItemClick(note, position)
            }
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }


}