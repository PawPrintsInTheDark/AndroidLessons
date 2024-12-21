package com.example.androidlessons.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.replace
import com.example.androidlessons.R

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name")
        val password = arguments?.getString("password")

        val userNameSignInET = view.findViewById<EditText>(R.id.userSignInET)
        val passwordSignInET = view.findViewById<EditText>(R.id.passwordSignInET)
        val loginSignInBTN = view.findViewById<Button>(R.id.loginSignInBTN)

        loginSignInBTN.setOnClickListener {
            val userNameSignIn = userNameSignInET.text.toString()
            val passwordSignIn = passwordSignInET.text.toString()

            if (name != userNameSignIn || password != passwordSignIn) {
                Toast.makeText(context, "Неверные данные!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                parentFragmentManager.beginTransaction().replace(R.id.containerID, MainFragment()).commit()
                Toast.makeText(context, "Вы успешно авторизованны", Toast.LENGTH_SHORT).show()
            }

        }
    }
}