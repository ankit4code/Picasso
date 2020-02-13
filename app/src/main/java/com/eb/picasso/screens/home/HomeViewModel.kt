package com.eb.picasso.screens.home


import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.eb.picasso.commons.ApiService
import com.eb.picasso.commons.base.BaseViewModel
import com.eb.picasso.commons.models.Picture
import com.eb.picasso.commons.models.PictureDao
import com.eb.picasso.commons.utils.API_KEY
import com.eb.picasso.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel internal constructor(
    private val apiService: ApiService, mContext : Context
) : BaseViewModel(){

    private val mDB=AppDatabase.getInstance(mContext)
    /*private val _movieDAO: MutableLiveData<PictureDao> = MutableLiveData()

    val movieLiveData : LiveData<PictureDao> get() = _movieDAO
*/
    init {
        fetchMovies(1)
    }

    fun fetchMovies(page:Int){

        launch {
            changeState(load = true)
            val movieDAO = withContext(Dispatchers.IO) {
                apiService.fetchImages("model",API_KEY,page)
            }
            mDB.pictureDao().insertAll(movieDAO.hits)
            Log.e("WIG",movieDAO.toString());
            changeState(load = false)
            //_movieDAO.postValue(movieDAO)
        }
    }

    fun getAllPictures() = mDB.pictureDao().getPicture()
}