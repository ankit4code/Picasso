package com.eb.picasso.commons.models

data class PictureDao(
    val total:Long,
    val totalHits:Int,
    val hits:MutableList<Picture>
)

data class Picture(
    val id:Long,
    val tags:String,
    val largeImageURL:String
)