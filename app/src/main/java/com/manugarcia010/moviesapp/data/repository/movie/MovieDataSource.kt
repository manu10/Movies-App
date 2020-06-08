package com.manugarcia010.moviesapp.data.repository.movie

import com.manugarcia010.moviesapp.domain.Response
import com.manugarcia010.moviesapp.domain.model.Movie

interface MovieDataSource {

    interface Remote {
        suspend fun getPopularMovies(): Response<List<Movie>>
        suspend fun getTopRatedMovies(): Response<List<Movie>>
        suspend fun searchMovies(searchTerm: String): Response<List<Movie>>
    }

    interface Local : Remote {
        suspend fun saveMovies(movies: List<Movie>)
        suspend fun getMovie(movieId: Int): Movie
    }

}