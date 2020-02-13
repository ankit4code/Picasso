package com.eb.picasso.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eb.picasso.commons.models.Picture

@Dao
interface PictureDao {
    @Query("SELECT * FROM pictures ORDER BY pic_id")
    fun getPicture(): LiveData<List<Picture>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(picture : List<Picture>)
}
