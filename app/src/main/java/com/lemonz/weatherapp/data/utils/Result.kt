package com.lemonz.weatherapp.data.utils

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T?) : Result<T>()
    data class Error(val exception: String?) : Result<Nothing>()
    object InProgress : Result<Nothing>()

    val extractData: T?
        get() = when (this) {
            is Success -> data
            is Error -> null
            is InProgress -> null
        }
}