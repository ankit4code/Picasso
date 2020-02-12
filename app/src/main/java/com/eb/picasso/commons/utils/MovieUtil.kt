package com.eb.picasso.commons.utils

class MovieUtil{
    fun getImageUrl(path:String):String {
        val url="https://image.tmdb.org/t/p/w500$path"
        print("))))$url")
        return url
    }
}

