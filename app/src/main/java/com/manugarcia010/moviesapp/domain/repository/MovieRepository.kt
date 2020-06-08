package com.manugarcia010.moviesapp.domain.repository

import com.manugarcia010.moviesapp.domain.Response
import com.manugarcia010.moviesapp.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): Response<List<Movie>>
    suspend fun getTopRatedMovies(): Response<List<Movie>>
    suspend fun getMovie(movieId: Int): Movie
}