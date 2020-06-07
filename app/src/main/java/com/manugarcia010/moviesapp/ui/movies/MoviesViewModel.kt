package com.manugarcia010.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manugarcia010.moviesapp.domain.Response
import com.manugarcia010.moviesapp.domain.model.Movie
import com.manugarcia010.moviesapp.domain.usecase.GetPopularMovies
import com.manugarcia010.moviesapp.ui.extensions.toPresentationMovie
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel  @Inject constructor(val getMovies: GetPopularMovies) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : MutableLiveData<String> = _errorMessage
    private val _items = MutableLiveData<List<MovieUI>>().apply { value = emptyList() }
    val items: MutableLiveData<List<MovieUI>> = _items

    fun onRefresh() {
        loadMovies()
    }

    fun loadMovies() {
        showLoading()
        viewModelScope.launch {
            val movies =  getMovies()
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
}

