package com.manugarcia010.moviesapp

import com.manugarcia010.moviesapp.domain.model.Movie

object FakeDataGenerator {
    fun getPopularMovies() =
        listOf(
            Movie(
                3,
                "Fake overview 3",
                "Fake poster path 3",
                30.0,
                "Fake Title 3"
            ),
            Movie(
                2,
                "Fake overview 2",
                "Fake poster path 2",
                20.0,
                "Fake Title 2"
            ),
            Movie(
                1,
                "Fake overview 1",
                "Fake poster path 2",
                10.0,
                "Fake Title 2"
            )
        )
}
