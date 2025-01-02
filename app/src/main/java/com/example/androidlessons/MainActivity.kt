package com.example.androidlessons

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.androidlessons.adapter.MainAdapter
import com.example.androidlessons.adapter.MainLoadStateAdapter
import com.example.androidlessons.databinding.ActivityMainBinding
import com.example.androidlessons.model.RetrofitInstance
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(RetrofitInstance.api, "ee3b7bce1c7090fc83a092263926e6a9")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MainAdapter()
        binding.recyclerView.adapter = adapter.withLoadStateFooter(MainLoadStateAdapter())

        lifecycleScope.launch {
            viewModel.data.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        adapter.addLoadStateListener { loadState ->
            binding.progressBar.visibility = if (loadState.source.refresh is LoadState.Loading) {
                View.VISIBLE
            } else {
                View.GONE
            }

            val errorState = loadState.source.refresh as? LoadState.Error
            if (errorState != null) {
                binding.errorMessage.visibility = View.VISIBLE
                binding.errorMessage.text = errorState.error.localizedMessage
            } else {
                binding.errorMessage.visibility = View.GONE
            }
        }
    }
}
