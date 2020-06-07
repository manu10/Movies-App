package com.manugarcia010.moviesapp.data.repository.movie

import com.manugarcia010.moviesapp.domain.Response
import com.manugarcia010.moviesapp.domain.model.Movie

interface MovieDataSource {

    interface Remote {
        suspend fun getPopularMovies(): Response<List<Movie>>
    }

    interface Local : Remote {
        suspend fun saveMovies(movies: List<Movie>)
    }

}