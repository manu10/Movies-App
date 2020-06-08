package com.manugarcia010.moviesapp.di

import android.content.Context
import com.manugarcia010.moviesapp.MoviesApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    MoviesDIModule::class,
    MovieDetailsDIModule::class,
    UiModule::class
])
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: MoviesApp): Builder
        fun build(): AppComponent
    }
    fun inject(app: MoviesApp)
}