package com.manugarcia010.moviesapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module(includes = [
    MoviesDIModule::class
])
class UiModule {

    @Provides
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory =
        AppViewModelFactory(providers)
}