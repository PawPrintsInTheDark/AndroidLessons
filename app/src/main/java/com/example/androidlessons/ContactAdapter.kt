package com.example.androidlessons

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val context: Context, private val listener: ContactClickListener) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    private val contacts = ArrayList<Contact>()

    fun updateList(newList:List<Contact>){
        contacts.clear()
        contacts.addAll(newList)
        notifyDataSetChanged()
    }
    inner class ContactViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val itemSurnameTV : TextView = itemView.findViewById(R.id.itemSurnameTV)
        private val itemPhoneTV : TextView = itemView.findViewById(R.id.itemPhoneTV)
        private val timestampTV : TextView = itemView.findViewById(R.id.itemTimesTV)
        val itemIconDeleteIV : ImageView = itemView.findViewById(R.id.itemIconDeleteIV)

        fun bind(contact: Contact){
            itemSurnameTV.text = contact.surname
            itemPhoneTV.text = contact.phone
            timestampTV.text = contact.timesTamp



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val viewHolder = ContactViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false))
        viewHolder.itemIconDeleteIV.setOnClickListener{
        listener.onItemClicked(contacts[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contacts[position]
        holder.bind(currentContact)
    }

    interface ContactClickListener{
        fun onItemClicked(contact: Contact)
    }
}