package com.manugarcia010.moviesapp.domain

sealed class Response<out T> {
    data class Success<out T>(val data: T, val freshData: Boolean = true) : Response<T>()

    class Error(val exception: Throwable) : Response<Nothing>()
}