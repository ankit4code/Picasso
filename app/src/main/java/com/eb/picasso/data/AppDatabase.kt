package com.eb.picasso.data

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eb.picasso.commons.models.Picture
import com.eb.picasso.commons.utils.DATABASE_NAME

@Database(entities = [Picture::class], version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun pictureDao(): PictureDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}
