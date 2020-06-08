package com.manugarcia010.moviesapp.data.repository.movie

import com.manugarcia010.moviesapp.data.db.MovieDao
import com.manugarcia010.moviesapp.data.exception.DataNotAvailableException
import com.manugarcia010.moviesapp.domain.Response
import com.manugarcia010.moviesapp.domain.model.Movie

class MovieLocalDataSource(
        private val movieDao: MovieDao
)
    : MovieDataSource.Local {

    override suspend fun getPopularMovies(): Response<List<Movie>> {
        val movies = movieDao.getPopularMovies()
        return if (movies.isNotEmpty()) {
            Response.Success(movies)
        } else {
            Response.Error(DataNotAvailableException())
        }
    }

    override suspend fun getTopRatedMovies(): Response<List<Movie>> {
        val movies = movieDao.getTopRatedMovies()
        return if (movies.isNotEmpty()) {
            Response.Success(movies)
        } else {
            Response.Error(DataNotAvailableException())
        }
    }

    override suspend fun getMovie(movieId: Int) =
        movieDao.getMovie(movieId)

    override suspend fun saveMovies(movies: List<Movie>) {
        movieDao.saveMovies(movies)
    }
}