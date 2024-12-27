package com.example.androidlessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidlessons.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.RedirectSignUpTV.setOnClickListener{
            findNavController().navigate(R.id.registerFragment)
        }
        return binding.root
    }

    private fun login() {
        val email = binding.emailET.text.toString()
        val password = binding.passwordET.text.toString()

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(requireContext(), "Успешно вошёл в систему", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.mailFragment)
            }else{
                Toast.makeText(requireContext(), "Не вошёл в систему", Toast.LENGTH_SHORT).show()
            }
            binding.RedirectSignUpTV.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}