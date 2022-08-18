package com.imdb.core.data


sealed class Resources {
    object Loading : Resources()
    data class Success<T>(val data: T) : Resources()
    data class Error(val error: Throwable) : Resources()
}


