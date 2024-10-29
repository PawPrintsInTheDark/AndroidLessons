package com.example.androidlessons

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class MyDialog: DialogFragment() {
    private var removable: Removable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        removable = context as Removable?
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val user = requireArguments().getParcelable("user") ?: User("", 0)
        val builder = AlertDialog.Builder(
            requireActivity()
        )
        return builder
            .setTitle("Внимание!")
            .setMessage("Удалить пользователя ${user.name}?")
            .setPositiveButton("Да"){ _, _ ->
                Toast.makeText(context, "Пользователь \"${user.name}\" удалён", Toast.LENGTH_SHORT).show()
                removable?.remove(user)
            }
            .setNegativeButton("Нет", null)
            .create()
    }
}