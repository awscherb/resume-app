package com.awscherb.resume.data

sealed class LoadState<T> {
    data class Loading<T>(val t: Unit = Unit) : LoadState<T>()
    data class Success<T>(val data: T) : LoadState<T>()

    data class Error<T>(val error: Throwable) : LoadState<T>()
}

fun <T> Loading() = LoadState.Loading<T>()
