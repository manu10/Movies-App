package com.manugarcia010.moviesapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manugarcia010.moviesapp.domain.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}