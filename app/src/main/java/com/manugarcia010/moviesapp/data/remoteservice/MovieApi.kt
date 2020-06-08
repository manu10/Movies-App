package com.manugarcia010.moviesapp.data.remoteservice

import com.manugarcia010.moviesapp.data.remoteservice.model.MovieResponse
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse
}