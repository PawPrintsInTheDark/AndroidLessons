package com.example.androidlessons.ui.personalinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.androidlessons.databinding.FragmentPersonalInfoBinding

class PersonalInfoFragment : Fragment() {
    private var _binding: FragmentPersonalInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val chatViewModel = ViewModelProvider(this)[PersonalInfoViewModel::class.java]
        _binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        val textView = binding.personalInfo
        chatViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        binding.editButton.setOnClickListener { showEditDialog() }

        return binding.root
    }

    private fun showEditDialog() {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(activity)
        builder.setTitle("Введите личные данные")

        val input = EditText(activity)
        builder.setView(input)

        builder.setPositiveButton("Сохранить") { _, _ ->
            binding.personalInfo.text = input.text.toString()
        }
        builder.setNegativeButton("Отмена") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
