package com.manugarcia010.moviesapp.data.repository.movie

import com.manugarcia010.moviesapp.domain.Response
import com.manugarcia010.moviesapp.domain.model.Movie
import com.manugarcia010.moviesapp.domain.repository.MovieRepository

class MovieRepositoryImpl constructor(
    private val movieRemote: MovieDataSource.Remote,
    private val movieLocal: MovieDataSource.Local
) : MovieRepository {

    override suspend fun getPopularMovies(): Response<List<Movie>> {
        return getPopularMoviesFromRemoteDataSource()
    }

    override suspend fun getTopRatedMovies(): Response<List<Movie>> {
        return getTopRatedMoviesFromRemoteDataSource()
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return movieLocal.getMovie(movieId)
    }

    private suspend fun getTopRatedMoviesFromRemoteDataSource(): Response<List<Movie>> {
        return when (val response = movieRemote.getTopRatedMovies()) {
            is Response.Success -> {
                saveMovies(response.data)
                return response
            }

            is Response.Error -> {
                getTopRatedMoviesFromLocalDataSource()
            }
        }
    }

    private suspend fun getPopularMoviesFromRemoteDataSource(): Response<List<Movie>> {

        return when (val response = movieRemote.getPopularMovies()) {
            is Response.Success -> {
                saveMovies(response.data)
                return response
            }

            is Response.Error -> {
                getPopularMoviesFromLocalDataSource()
            }
        }
    }

    private suspend fun getPopularMoviesFromLocalDataSource(): Response<List<Movie>> {
        return movieLocal.getPopularMovies() //todo: add something to let the user know that the results may be outdated
    }

    private suspend fun getTopRatedMoviesFromLocalDataSource(): Response<List<Movie>> {
        return movieLocal.getTopRatedMovies() //todo: add something to let the user know that the results may be outdated
    }

    private suspend fun saveMovies(movies: List<Movie>) {
        movieLocal.saveMovies(movies)
    }
}