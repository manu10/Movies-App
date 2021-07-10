package com.manugarcia010.moviesapp.di

import com.manugarcia010.moviesapp.domain.repository.MovieRepository
import com.manugarcia010.moviesapp.domain.usecase.GetMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MovieDetailsDIModule {
    @Provides
    fun provideGetMovie(repository: MovieRepository): GetMovie = GetMovie(repository)
}
