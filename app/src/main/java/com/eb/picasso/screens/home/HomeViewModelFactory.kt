package com.eb.picasso.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eb.picasso.commons.ApiService

class HomeViewModelFactory(
    private val apiService: ApiService,
    private val context: Context
): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = HomeViewModel(apiService,context) as T
}