package com.example.androidlessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidlessons.databinding.FragmentRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        auth = Firebase.auth
        binding.btnSSigned.setOnClickListener {
            signUpUser()
        }
        binding.tvRedirectLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        return binding.root
    }

    private fun signUpUser() {
        val email = binding.emailSignUpET.text.toString()
        val pass = binding.passwordSignUpET.text.toString()
        val confirmPassword = binding.passwordConfirmSignUpET.text.toString()

        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(
                requireContext(),
                "Адрес электронной почты и пароль не могут быть пустыми",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (pass != confirmPassword) {
            Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(requireContext(), "Успешно зарегистрирован", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.mailFragment)
            } else {
                // Обработка ошибок
                task.exception?.let { exception ->
                    Toast.makeText(requireContext(), exception.message, Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener { err ->
            Toast.makeText(requireContext(), err.message, Toast.LENGTH_SHORT).show()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}