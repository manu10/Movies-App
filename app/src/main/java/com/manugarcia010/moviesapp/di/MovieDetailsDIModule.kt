package com.manugarcia010.moviesapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manugarcia010.moviesapp.domain.repository.MovieRepository
import com.manugarcia010.moviesapp.domain.usecase.GetMovie
import com.manugarcia010.moviesapp.ui.details.MovieDetailsFragment
import com.manugarcia010.moviesapp.ui.details.MovieDetailsViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    MovieDetailsDIModule.ProvideViewModel::class,
    MovieDetailsDIModule.ProvideMovieDetails::class
])
abstract class MovieDetailsDIModule {

    @ContributesAndroidInjector(
        modules = [
            InjectViewModel::class
        ]
    )
    abstract fun bind(): MovieDetailsFragment

    @Module
    class ProvideMovieDetails {

        @Provides
        fun provideGetMovie(repository: MovieRepository): GetMovie
                = GetMovie(repository)
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(MovieDetailsViewModel::class)
        fun provideMovieDetailsViewModel(getMovie: GetMovie): ViewModel =
            MovieDetailsViewModel(getMovie)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideMoviesViewModel(
            factory: ViewModelProvider.Factory,
            target: MovieDetailsFragment
        ) = ViewModelProvider(target, factory).get(MovieDetailsViewModel::class.java)
    }

}
