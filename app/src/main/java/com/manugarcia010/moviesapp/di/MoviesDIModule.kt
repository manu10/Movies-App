package com.manugarcia010.moviesapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manugarcia010.moviesapp.data.remoteservice.MovieApi
import com.manugarcia010.moviesapp.data.db.MovieDao
import com.manugarcia010.moviesapp.data.repository.movie.MovieDataSource
import com.manugarcia010.moviesapp.data.repository.movie.MovieLocalDataSource
import com.manugarcia010.moviesapp.data.repository.movie.MovieRemoteDataSource
import com.manugarcia010.moviesapp.data.repository.movie.MovieRepositoryImpl
import com.manugarcia010.moviesapp.domain.repository.MovieRepository
import com.manugarcia010.moviesapp.domain.usecase.GetMovies
import com.manugarcia010.moviesapp.ui.movies.MoviesFragment
import com.manugarcia010.moviesapp.ui.movies.MoviesViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [
    MoviesDIModule.ProvideViewModel::class,
    MoviesDIModule.ProvideMovies::class
])
abstract class MoviesDIModule {

    @ContributesAndroidInjector(
        modules = [
            InjectViewModel::class
        ]
    )
    abstract fun bind(): MoviesFragment

    @Module
    class ProvideMovies {

        @Provides
        internal fun providesMovieApi(retrofit: Retrofit): MovieApi {
            return retrofit.create(MovieApi::class.java)
        }

        @Provides
        fun provideMovieRemoteDataSource(movieApi: MovieApi): MovieDataSource.Remote
                = MovieRemoteDataSource(movieApi)

        @Provides
        fun provideMovieLocalDataSource(movieDao: MovieDao): MovieDataSource.Local
                = MovieLocalDataSource(movieDao)

        @Provides
        fun provideMovieRepository(movieRemote: MovieDataSource.Remote, movieLocal: MovieDataSource.Local): MovieRepository
                = MovieRepositoryImpl(movieRemote, movieLocal)

        @Provides
        fun provideGetMovies(repository: MovieRepository): GetMovies
                = GetMovies(repository)
    }


    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(MoviesViewModel::class)
        fun provideAddNoteViewModel(getMovies : GetMovies): ViewModel =
            MoviesViewModel(getMovies)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideMoviesViewModel(
            factory: ViewModelProvider.Factory,
            target: MoviesFragment
        ) = ViewModelProvider(target, factory).get(MoviesViewModel::class.java)
    }

}
