package com.manugarcia010.moviesapp.data.remoteservice.model

import com.squareup.moshi.Json


data class MovieResponse(

        val page : Int,

        @field:Json(name = "total_results")
        val totalResults : Int,

        @field:Json(name = "total_pages")
        val totalPages : Int,

        @field:Json(name = "results")
        val movies : List<MovieRemote>
)