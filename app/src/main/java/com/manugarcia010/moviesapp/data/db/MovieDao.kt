package com.manugarcia010.moviesapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manugarcia010.moviesapp.domain.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    suspend fun getPopularMovies(): List<Movie>

    @Query("SELECT * FROM movies ORDER BY vote_average DESC")
    suspend fun getTopRatedMovies(): List<Movie>

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

    @Query("SELECT * FROM movies WHERE id == :movieId")
    suspend fun getMovie(movieId: Int): Movie

    @Query("SELECT * FROM movies WHERE title LIKE :searchTerm")
    suspend fun searchMovies(searchTerm: String): List<Movie>
}