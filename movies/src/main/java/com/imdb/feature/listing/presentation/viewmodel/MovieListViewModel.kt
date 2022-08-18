package com.imdb.feature.listing.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imdb.core.base.BaseViewModel
import com.imdb.core.data.ViewState
import com.imdb.designsystem.list.model.ItemListModel
import com.imdb.feature.listing.data.model.MovieListResponse
import com.imdb.feature.listing.domain.MovieListUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieListUseCase: MovieListUseCase) : BaseViewModel() {

    private val _movies = MutableLiveData<List<ItemListModel>>()
    val movies: LiveData<List<ItemListModel>> = _movies

    fun fetchMovies() {
        viewModelScope.launch {
            movieListUseCase.resultFlow.catch {
            }.collect {
                when (it) {
                    is ViewState.Success<*> -> {
                        val list = it.data as MovieListResponse
                        _movies.value = list.items?.map {
                            ItemListModel(
                                id = it.id ?: "",
                                title = it.title ?: "",
                                description = it.fullTitle ?: "",
                                imageUrl = it.imageUrl ?: ""
                            )
                        }
                    }

                    is ViewState.Error -> {
                        it.error
                    }
                }
            }

            movieListUseCase.launch(Unit)
        }
    }

    fun fetchedMovies() = _movies.value ?: listOf()
}