package com.manugarcia010.moviesapp.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.manugarcia010.moviesapp.BuildConfig
import com.manugarcia010.moviesapp.FakeDataGenerator
import com.manugarcia010.moviesapp.LiveDataTestUtil.getValue
import com.manugarcia010.moviesapp.MainCoroutineRule
import com.manugarcia010.moviesapp.domain.repository.MovieRepository
import com.manugarcia010.moviesapp.domain.usecase.GetMovie
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {
    private lateinit var viewModel : MovieDetailsViewModel

    @Mock
    lateinit var movieRepository: MovieRepository

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        MockitoAnnotations.initMocks(this)
        viewModel = MovieDetailsViewModel(
            GetMovie(movieRepository)
        )
    }
    @Test
    fun getMovieFromRepositoryAndLoadIntoView() = runBlockingTest {
        val fakeMovie = FakeDataGenerator.getMovie()
        Mockito.`when`(movieRepository.getMovie(any())).thenReturn(fakeMovie)

        viewModel.start(1)

        // Then progress indicator is hidden
        assertThat(getValue(viewModel.dataLoading)).isFalse()

        // Then verify that the view was notified
        assertThat(getValue(viewModel.movie).id).isEqualTo(fakeMovie.id)
        assertThat(getValue(viewModel.movie).title).isEqualTo(fakeMovie.title)
        assertThat(getValue(viewModel.movie).overview).isEqualTo(fakeMovie.overview)
        assertThat(getValue(viewModel.movie).imageUrl).isEqualTo(BuildConfig.MOVIE_IMG_URL_PREFIX + fakeMovie.posterPath)
    }

//    @Test todo: handle errors
//    fun movieDetailViewModel_repositoryError() {
//        // Given a repository that returns errors
//
//        // Then verify that data is not available
//    }

    @Test
    fun loadMovie_loading() = runBlockingTest{
        val fakeMovie = FakeDataGenerator.getMovie()
        Mockito.`when`(movieRepository.getMovie(any())).thenReturn(fakeMovie)

        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()
        viewModel.start(1)

        // Then progress indicator is visible
        assertThat(getValue(viewModel.dataLoading)).isTrue()

        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        assertThat(getValue(viewModel.dataLoading)).isFalse()
    }

}