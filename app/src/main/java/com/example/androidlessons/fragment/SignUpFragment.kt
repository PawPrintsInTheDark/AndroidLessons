package com.example.androidlessons.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.androidlessons.R

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userNameSignUpET = view.findViewById<EditText>(R.id.userSignUpET)
        val passwordSignUpET = view.findViewById<EditText>(R.id.passwordSignUpET)
        val loginSignUpBTN = view.findViewById<Button>(R.id.loginSignUpBTN)
        loginSignUpBTN.setOnClickListener{
            val userNameSignUp = userNameSignUpET.text.toString()
            val passwordSignUp = passwordSignUpET.text.toString()
            if (passwordSignUp.isEmpty() || userNameSignUp.isEmpty()){
                Toast.makeText(context, "Данные не введены", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val bundle = Bundle()
            val signInFragment = SignInFragment()
            bundle.putString("name", userNameSignUp)
            bundle.putString("password", passwordSignUp)
            signInFragment.arguments = bundle
            parentFragmentManager.beginTransaction().replace(R.id.containerID,signInFragment).commit()
            Toast.makeText(context, "Вы успешно зарегистрированы", Toast.LENGTH_SHORT).show()

        }
    }
}