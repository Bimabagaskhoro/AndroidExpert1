package com.bimabagaskhoro.submission.core.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bimabagaskhoro.submission.core.source.local.entitiy.TvShowEntity

@Database(entities = [TvShowEntity::class], version = 1, exportSchema = false)
abstract class TvShowDatabase : RoomDatabase() {

    abstract fun tvShowDao(): TvShowDao

    companion object {

        @Volatile
        private var INSTANCE: TvShowDatabase? = null

        fun getInstance(context: Context): TvShowDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TvShowDatabase::class.java,
                    "tv_show.db"
                ).build()
                INSTANCE = instance
                instance
            }
    }
}