package com.imdb.feature.listing.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imdb.core.base.BaseViewModel
import com.imdb.designsystem.list.model.ItemListModel
import com.imdb.feature.listing.domain.MovieListUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieListUseCase: MovieListUseCase) : BaseViewModel() {

    private val _movies = MutableLiveData<List<ItemListModel>>()
    val movies: LiveData<List<ItemListModel>> = _movies

    fun fetchMovies() {
        viewModelScope.launch {
            movieListUseCase.fetchMovies().catch {
                Log.i("log", it.message, it)
            }.collect {
                _movies.value = it.items?.map { movie ->
                    ItemListModel(
                        title = movie.title ?: "",
                        description = movie.fullTitle ?: "",
                        imageUrl = movie.imageUrl ?: ""
                    )
                }
            }
        }
    }

    fun fetchedMovies() = _movies.value ?: listOf()
}