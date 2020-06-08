package com.manugarcia010.moviesapp.domain.usecase

import com.manugarcia010.moviesapp.domain.repository.MovieRepository

class SearchMovies(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(searchTerm: String) =
            movieRepository.searchMovies(searchTerm)

}