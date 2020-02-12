package com.eb.movieapp.common


import android.content.Context
import com.eb.picasso.commons.ApiService
import com.eb.picasso.commons.utils.BASE_URL
import com.eb.picasso.commons.utils.SharedPrefWrapper


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val applicationModules = module {


    single { createOkHttpClient() }
    single { androidApplication().getSharedPreferences("picasso_db", Context.MODE_PRIVATE) }
    single { SharedPrefWrapper(get()) }


    single<ApiService> {
        createWebService(
            get(),
            BASE_URL
        )
    }
}


fun createOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(logging)
        .build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    url: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

