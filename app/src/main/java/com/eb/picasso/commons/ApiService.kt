package com.eb.picasso.commons

import com.eb.picasso.commons.models.PictureDao
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api")
    suspend fun fetchImages(
        @Query("q") type:String,
        @Query("key") apiKey:String
        ): PictureDao
}
