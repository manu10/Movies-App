package com.manugarcia010.moviesapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manugarcia010.moviesapp.domain.usecase.GetMovie
import com.manugarcia010.moviesapp.ui.extensions.toPresentationMovie
import com.manugarcia010.moviesapp.ui.movies.MovieUI
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(val getMovie : GetMovie) : ViewModel() {

    private val _movie = MutableLiveData<MovieUI>()
    val movie: LiveData<MovieUI> = _movie
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading
    private val _errorMessage = MutableLiveData<String>()
    //val errorMessage : MutableLiveData<String> = _errorMessage todo: handle errors

    fun start(taskId: Int) {
        showLoading()
        viewModelScope.launch {
            _movie.value = getMovie(taskId).toPresentationMovie()
            hideProgress()
        }
    }

    private fun showLoading() {
        _dataLoading.value = true
    }

    private fun hideProgress() {
        _dataLoading.value = false
    }
}

