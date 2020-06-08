package com.manugarcia010.moviesapp.data.remoteservice

import com.manugarcia010.moviesapp.data.remoteservice.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") searchTerm: String): MovieResponse
}