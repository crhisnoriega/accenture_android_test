package com.imdb.core.data

sealed class ViewState {
    object Loading : ViewState()
    object Empty : ViewState()
    data class Success<T>(val data: T) : ViewState()
    data class Error(val error: Throwable) : ViewState()

}