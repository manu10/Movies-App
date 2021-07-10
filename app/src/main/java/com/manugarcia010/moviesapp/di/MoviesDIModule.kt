package com.manugarcia010.moviesapp.di

import com.manugarcia010.moviesapp.data.remoteservice.MovieApi
import com.manugarcia010.moviesapp.data.db.MovieDao
import com.manugarcia010.moviesapp.data.repository.movie.MovieDataSource
import com.manugarcia010.moviesapp.data.repository.movie.MovieLocalDataSource
import com.manugarcia010.moviesapp.data.repository.movie.MovieRemoteDataSource
import com.manugarcia010.moviesapp.data.repository.movie.MovieRepositoryImpl
import com.manugarcia010.moviesapp.domain.repository.MovieRepository
import com.manugarcia010.moviesapp.domain.usecase.GetMovies
import com.manugarcia010.moviesapp.domain.usecase.SearchMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class MoviesDIModule {

    @Provides
    internal fun providesMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    fun provideMovieRemoteDataSource(movieApi: MovieApi): MovieDataSource.Remote =
        MovieRemoteDataSource(movieApi)

    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieDataSource.Local =
        MovieLocalDataSource(movieDao)

    @Provides
    fun provideMovieRepository(
        movieRemote: MovieDataSource.Remote,
        movieLocal: MovieDataSource.Local
    ): MovieRepository = MovieRepositoryImpl(movieRemote, movieLocal)

    @Provides
    fun provideGetMovies(repository: MovieRepository): GetMovies = GetMovies(repository)

    @Provides
    fun provideSearchMovies(repository: MovieRepository): SearchMovies = SearchMovies(repository)
}
