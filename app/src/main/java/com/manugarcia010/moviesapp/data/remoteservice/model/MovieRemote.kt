package com.manugarcia010.moviesapp.data.remoteservice.model

import com.squareup.moshi.Json

data class MovieRemote(
    val id: Int,

    val title: String,

    val overview: String,

    @field:Json(name = "poster_path")
    val poster_path: String?,

    val popularity: Double,

    @field:Json(name = "vote_count")
    val voteCount: Int,

    val video: Boolean,

    val adult: Boolean,

    @field:Json(name = "backdrop_path")
    val backdropPath: String?,

    @field:Json(name = "original_language")
    val originalLanguage: String,

    @field:Json(name = "original_title")
    val originalTitle: String,

    @field:Json(name = "genre_ids")
    val genreIds: List<Int>,

    @field:Json(name = "vote_average")
    val voteAverage: Double,

    @field:Json(name = "release_date")
    val releaseDate: String
)