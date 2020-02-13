package com.eb.picasso.commons.utils

import androidx.multidex.MultiDexApplication
import com.eb.movieapp.common.applicationModules
import com.eb.picasso.commons.daggers.DaggerPicassoComponent
import com.eb.picasso.commons.daggers.PicassoComponent
import com.eb.picasso.commons.daggers.RetrofitModule
import org.koin.android.ext.android.startKoin

class Application : MultiDexApplication(){

    companion object {
        val picassoComponent: PicassoComponent by lazy {
            DaggerPicassoComponent
                .builder()
                .retrofitModule(RetrofitModule())
                .build()
        }
    }



    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(applicationModules),
            loadPropertiesFromFile = true
        )
    }
}
