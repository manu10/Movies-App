package com.manugarcia010.moviesapp.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.manugarcia010.moviesapp.FakeDataGenerator
import com.manugarcia010.moviesapp.LiveDataTestUtil
import com.manugarcia010.moviesapp.MainCoroutineRule
import com.manugarcia010.moviesapp.assertLiveDataEventTriggered
import com.manugarcia010.moviesapp.data.exception.DataNotAvailableException
import com.manugarcia010.moviesapp.domain.Response
import com.manugarcia010.moviesapp.domain.repository.MovieRepository
import com.manugarcia010.moviesapp.domain.usecase.GetPopularMovies
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    private lateinit var viewModel : MoviesViewModel

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
        viewModel = MoviesViewModel(
            GetPopularMovies(movieRepository)
        )
    }

    @Test
    fun loadPopularMovies_success() = runBlockingTest{
        // Make the repository return a correct value
        Mockito.`when`(movieRepository.getPopularMovies()).thenReturn(
            Response.Success(FakeDataGenerator.getPopularMovies()))

        // Load weather data
        viewModel.loadMovies()

        // Then progress indicator is hidden
        assertThat(LiveDataTestUtil.getValue(viewModel.dataLoading)).isFalse()

        // And the list of items is not empty
        assertThat(LiveDataTestUtil.getValue(viewModel.items)).isNotEmpty()

    }

    @Test
    fun loadPopularMovies_failure() = runBlockingTest{
        // Make the repository return a correct value
        Mockito.`when`(movieRepository.getPopularMovies()).thenReturn(
            Response.Error(DataNotAvailableException()))

        // Load weather data
        viewModel.loadMovies()

        // Then progress indicator is hidden
        assertThat(LiveDataTestUtil.getValue(viewModel.dataLoading)).isFalse()

        // And the list of items is not empty
        assertThat(LiveDataTestUtil.getValue(viewModel.errorMessage)).isNotEmpty()

    }


    @Test
    fun onRefresh_getPopularMovies_isCalled() = runBlockingTest{
        // Make the repository return a correct value
        viewModel.onRefresh()
        verify(movieRepository).getPopularMovies()
    }


    @Test
    fun clickOnMovie_setsEventToNavigate() {
        val movieId = 1
        viewModel.openMovieDetails(movieId)

        // Then the event is triggered
        assertLiveDataEventTriggered(viewModel.openMovieDetailsEvent, movieId)
    }


}