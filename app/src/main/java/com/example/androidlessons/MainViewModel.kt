package com.example.androidlessons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.androidlessons.model.MovieApi
import com.example.androidlessons.pagination.MainPagingSource

class MainViewModel(
    private val movieApi: MovieApi,
    private val apiKey: String
) : ViewModel() {
    val data = Pager(
        PagingConfig(pageSize = 20, enablePlaceholders = false, initialLoadSize = 20)
    ) {
        MainPagingSource(movieApi, apiKey)
    }.flow.cachedIn(viewModelScope)
}

class MainViewModelFactory(
    private val movieApi: MovieApi,
    private val apiKey: String
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(movieApi, apiKey) as T
        }
        throw IllegalArgumentException("Неизвестный класс ViewModel")
    }
}