package com.manugarcia010.moviesapp.domain.usecase

import com.manugarcia010.moviesapp.domain.repository.MovieRepository

class GetMovies(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(currentOrderCriterion: MoviesOrderCriteria) =
        if (currentOrderCriterion == MoviesOrderCriteria.POPULAR)
            movieRepository.getPopularMovies()
        else
            movieRepository.getTopRatedMovies()

}

enum class MoviesOrderCriteria {
    POPULAR,
    TOP_RATED
}