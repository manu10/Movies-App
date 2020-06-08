package com.manugarcia010.moviesapp.data.utils

import com.manugarcia010.moviesapp.data.remoteservice.model.MovieRemote
import com.manugarcia010.moviesapp.domain.model.Movie

fun MovieRemote.toDomainMovie() = Movie(
    id = id,
    posterPath = poster_path,
    overview = overview,
    popularity = popularity,
    voteAverage = voteAverage,
    title = title
)