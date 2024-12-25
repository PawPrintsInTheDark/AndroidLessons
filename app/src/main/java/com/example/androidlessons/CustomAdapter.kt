package com.example.androidlessons

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlessons.databinding.ListItemBinding

class CustomAdapter(
    private val context: Context,
    private val contactModelList: List<ContactModel>,
    private val onCallClick: (String) -> Unit,
    private val onMessageClick: (String) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: ContactModel) {
            binding.nameTV.text = contact.name
            binding.phoneTV.text = contact.phone

            binding.callButton.setOnClickListener {
                onCallClick(contact.phone)
            }
            binding.messageButton.setOnClickListener {
                onMessageClick(contact.phone)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding  = ListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactModelList[position])
    }

    override fun getItemCount(): Int {
        return contactModelList.size
    }
}
