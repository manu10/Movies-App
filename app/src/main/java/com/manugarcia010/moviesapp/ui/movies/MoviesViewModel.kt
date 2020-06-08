package com.manugarcia010.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manugarcia010.moviesapp.domain.Response
import com.manugarcia010.moviesapp.domain.model.Movie
import com.manugarcia010.moviesapp.domain.usecase.GetMovies
import com.manugarcia010.moviesapp.domain.usecase.MoviesOrderCriteria
import com.manugarcia010.moviesapp.domain.usecase.SearchMovies
import com.manugarcia010.moviesapp.ui.Event
import com.manugarcia010.moviesapp.ui.extensions.toPresentationMovie
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel  @Inject constructor(
    val getMovies: GetMovies,
    val searchMovies: SearchMovies
) : ViewModel() {

    private val _openMovieDetailsEvent = MutableLiveData<Event<Int>>()
    val openMovieDetailsEvent: LiveData<Event<Int>> = _openMovieDetailsEvent
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : MutableLiveData<String> = _errorMessage
    private val _items = MutableLiveData<List<MovieUI>>().apply { value = emptyList() }
    val items: MutableLiveData<List<MovieUI>> = _items

    private var currentOrderCriterion = MoviesOrderCriteria.POPULAR

    fun onRefresh() {
        loadMovies()
    }

    fun loadMovies() {
        showLoading()
        viewModelScope.launch {
            val movies = getMovies.invoke(currentOrderCriterion)
            hideProgress()
            processMoviesDataResponse(movies)
        }
    }

    private fun processMoviesDataResponse(response: Response<List<Movie>>) {
        when (response) {
            is Response.Success -> onLoadingDataSuccess(response)
            is Response.Error -> onLoadingDataFailure(response)
        }
    }

    private fun showLoading() {
        _dataLoading.value = true
    }

    private fun hideProgress() {
        _dataLoading.value = false
    }

    private fun onLoadingDataSuccess(response: Response.Success<List<Movie>>) {
        _items.value = response.data.map { it.toPresentationMovie() }
    }

    private fun onLoadingDataFailure(response: Response.Error) {
        //todo: Improve error handling
        errorMessage.value = response.exception.message
    }
    /**
     * Called by Data Binding.
     */
    fun openMovieDetails(movieId: Int) {
        _openMovieDetailsEvent.value = Event(movieId)
    }

    fun setOrderCriteria(criterion: MoviesOrderCriteria?) {
        currentOrderCriterion = criterion ?: MoviesOrderCriteria.POPULAR
    }
}

