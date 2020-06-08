package com.manugarcia010.moviesapp.ui.extensions

import com.manugarcia010.moviesapp.BuildConfig
import com.manugarcia010.moviesapp.domain.model.Movie
import com.manugarcia010.moviesapp.ui.movies.MovieUI

fun Movie.toPresentationMovie() =
    MovieUI(
        id,
        title,
        overview,
        BuildConfig.MOVIE_IMG_URL_PREFIX + posterPath
    )