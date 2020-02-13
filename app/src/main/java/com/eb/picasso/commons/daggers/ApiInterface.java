package com.eb.picasso.commons.daggers;



import com.eb.picasso.commons.models.PictureDao;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface{

    @GET("api")
    Observable<PictureDao> searchPics(
            @Query("q") String keyword,
            @Query("key") String api
    );
}
