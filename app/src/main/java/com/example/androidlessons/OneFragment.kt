package com.example.androidlessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.androidlessons.databinding.FragmentOneBinding

class OneFragment : Fragment() {

    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar1)

        binding.button1.setOnClickListener {
            val name = binding.button1.text.toString()
            val action = OneFragmentDirections.actionOneFragmentToTwoFragment(name)
            val extras = FragmentNavigatorExtras(
                binding.button1 to "btn1"
            )
            findNavController().navigate(action, extras)
        }
        binding.button2.setOnClickListener {
            val name = binding.button2.text.toString()
            val action = OneFragmentDirections.actionOneFragmentToTwoFragment(name)
            val extras = FragmentNavigatorExtras(
                binding.button2 to "btn2"
            )
            findNavController().navigate(action, extras)
        }
        binding.button3.setOnClickListener {
            val name = binding.button3.text.toString()
            val action = OneFragmentDirections.actionOneFragmentToTwoFragment(name)
            val extras = FragmentNavigatorExtras(
                binding.button3 to "btn3"
            )
            findNavController().navigate(action, extras)
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