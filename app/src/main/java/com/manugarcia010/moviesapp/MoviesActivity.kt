package com.manugarcia010.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manugarcia010.moviesapp.ui.movies.MoviesFragment

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_activity)
    }
}
