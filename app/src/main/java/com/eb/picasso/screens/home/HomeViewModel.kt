package com.eb.picasso.screens.home


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.eb.picasso.commons.ApiService
import com.eb.picasso.commons.base.BaseViewModel
import com.eb.picasso.commons.models.PictureDao
import com.eb.picasso.commons.utils.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel internal constructor(private val apiService: ApiService) : BaseViewModel(){

    private val _movieDAO: MutableLiveData<PictureDao> = MutableLiveData()
    val movieLiveData : LiveData<PictureDao> get() = _movieDAO

    init {
        System.out.println("Initialized...................")
        getMovies()
    }

    fun getMovies(){
        System.out.println("called...................")
        launch {
            changeState(load = true)
            val movieDAO = withContext(Dispatchers.IO) {
                apiService.fetchImages("model",API_KEY)
            }
            Log.e("WIG",movieDAO.toString());
            changeState(load = false)
            _movieDAO.postValue(movieDAO)
        }
    }

}