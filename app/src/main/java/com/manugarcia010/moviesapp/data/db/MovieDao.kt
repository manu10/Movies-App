package com.manugarcia010.moviesapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manugarcia010.moviesapp.domain.model.Movie

@Dao
interface MovieDao {
    /**
     * Select all movies from the movies table.
     *
     * @return all movies.
     */
    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    suspend fun getPopularMovies(): List<Movie>

    /**
     * Insert all movies.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie?>?)

    /**
     * Delete all movies.
     */
    @Query("DELETE FROM movies")
    suspend fun deleteMovies()
}