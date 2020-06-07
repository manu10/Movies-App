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
import com.manugarcia010.moviesapp.domain.usecase.GetPopularMovies
import com.manugarcia010.moviesapp.ui.details.MovieDetailsFragment
import com.manugarcia010.moviesapp.ui.details.MovieDetailsViewModel
import com.manugarcia010.moviesapp.ui.movies.MoviesFragment
import com.manugarcia010.moviesapp.ui.movies.MoviesViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [
    MovieDetailsDIModule.ProvideViewModel::class
//    MovieDetailsDIModule.ProvideMovies::class
])
abstract class MovieDetailsDIModule {

    @ContributesAndroidInjector(
        modules = [
            InjectViewModel::class
        ]
    )
    abstract fun bind(): MovieDetailsFragment

//    @Module
//    class ProvideMovies {
//
//        @Provides
//        internal fun providesMovieApi(retrofit: Retrofit): MovieApi {
//            return retrofit.create(MovieApi::class.java)
//        }
//
//        @Provides
//        fun provideMovieRemoteDataSource(movieApi: MovieApi): MovieDataSource.Remote
//                = MovieRemoteDataSource(movieApi)
//
//        @Provides
//        fun provideMovieLocalDataSource(movieDao: MovieDao): MovieDataSource.Local
//                = MovieLocalDataSource(movieDao)
//
//        @Provides
//        fun provideMovieRepository(movieRemote: MovieDataSource.Remote, movieLocal: MovieDataSource.Local): MovieRepository
//                = MovieRepositoryImpl(movieRemote, movieLocal)
//
//        @Provides
//        fun provideGetMovies(repository: MovieRepository): GetPopularMovies
//                = GetPopularMovies(repository)
//    }


    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(MovieDetailsViewModel::class)
        fun provideAddNoteViewModel(): ViewModel =
            MovieDetailsViewModel()
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
