package com.manugarcia010.moviesapp.domain.usecase

import com.manugarcia010.moviesapp.domain.repository.MovieRepository

open class GetMovie(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(movieId: Int) = movieRepository.getMovie(movieId)

}
