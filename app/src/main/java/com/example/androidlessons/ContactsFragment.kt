package com.example.androidlessons

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlessons.databinding.FragmentContactsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import kotlin.math.log

class ContactsFragment : Fragment() {
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        binding.saveBTN.setOnClickListener {
            val name = binding.nameET.text.toString().trim()
            val phone = binding.phoneET.text.toString().trim()
            if (name.isBlank() || phone.isBlank()) return@setOnClickListener
            val contactAdd = ContactData(name, phone)
            addContact(contactAdd)
            with(binding){
                nameET.text.clear()
                phoneET.text.clear()
            }
            readContact()
        }
    }

    private fun addContact(contact : ContactData) {
        val id = FirebaseAuth.getInstance().currentUser!!.uid
        val database = Firebase.database.reference
            .child("users")
            .child(id)
        val map: HashMap<String, ContactData> = HashMap()
        map[contact.name.toString()] = contact
        database.updateChildren(map as Map<String, ContactData>)
        Log.e("test", "id: $id \n database: $database")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun readContact(){
        val id = FirebaseAuth.getInstance().currentUser!!.uid
        Firebase.database.reference.child("users")
            .child(id).get().addOnSuccessListener {
                val listContact = mutableListOf<ContactData>()
                for (element in it.children){
                    val contact: ContactData  = element.getValue(ContactData::class.java)!!
                    listContact.add(contact)
                }
                contactAdapter = ContactAdapter(listContact)
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
                binding.recyclerView.adapter = contactAdapter
                contactAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("FirebaseError", "Error writing to database", exception)
            }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireActivity().finishAffinity()
        return true
    }
}