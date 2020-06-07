package com.manugarcia010.moviesapp.domain.usecase

import com.manugarcia010.moviesapp.domain.repository.MovieRepository

open class GetPopularMovies(private val movieRepository: MovieRepository) {

    suspend operator fun invoke() = movieRepository.getPopularMovies()

}
