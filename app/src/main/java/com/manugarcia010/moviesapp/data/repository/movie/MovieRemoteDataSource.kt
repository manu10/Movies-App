package com.manugarcia010.moviesapp.data.repository.movie

import com.manugarcia010.moviesapp.data.remoteservice.MovieApi
import com.manugarcia010.moviesapp.data.utils.toDomainMovie
import com.manugarcia010.moviesapp.domain.Response
import com.manugarcia010.moviesapp.domain.model.Movie
import java.lang.Exception

class MovieRemoteDataSource(private val movieApi: MovieApi) : MovieDataSource.Remote {

    override suspend fun getPopularMovies(): Response<List<Movie>> {
        return try {
            val result = movieApi.getPopularMovies()
            Response.Success(result.movies.map {
                it.toDomainMovie()
            })
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun getTopRatedMovies(): Response<List<Movie>> {
        return try {
            val result = movieApi.getTopRatedMovies()
            Response.Success(result.movies.map {
                it.toDomainMovie()
            })
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}