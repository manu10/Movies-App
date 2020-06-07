package com.manugarcia010.moviesapp

import android.app.Application
import androidx.fragment.app.Fragment
import com.manugarcia010.moviesapp.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MoviesApp : Application(), HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun supportFragmentInjector() = fragmentInjector
}