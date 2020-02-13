package com.eb.picasso.commons.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class PictureDao(
    val total:Long,
    val totalHits:Int,
    val hits:MutableList<Picture>
)


@Entity(tableName = "pictures")
data class Picture(
    @PrimaryKey @ColumnInfo(name = "pic_id")
    val id:Long,
    val tags:String,
    val largeImageURL:String
)