package com.manugarcia010.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.manugarcia010.moviesapp.BuildConfig
import com.manugarcia010.moviesapp.MoviesApp
import com.manugarcia010.moviesapp.data.remoteservice.AuthorizationInterceptor
import com.manugarcia010.moviesapp.data.db.MovieDao
import com.manugarcia010.moviesapp.data.db.MovieDatabase
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class AppModule(){

    @Provides
    @Singleton
    fun provideContext(application: MoviesApp) : Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    open fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(AuthorizationInterceptor(BuildConfig.API_KEY))
            .readTimeout(20L, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    open fun providesDispatcher(okHttpClient: OkHttpClient): Dispatcher {
        // This can be used to cancel all pending request
        return okHttpClient.dispatcher()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    open fun providesMovieDataBase(applicationContext: Context): MovieDatabase {
        return Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java, BuildConfig.DATABASE
        ).build()
    }

    @Singleton
    @Provides
    open fun providesMovieDao(movieDataBase: MovieDatabase): MovieDao {
        return movieDataBase.movieDao()
    }

}