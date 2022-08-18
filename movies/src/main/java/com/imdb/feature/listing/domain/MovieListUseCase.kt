package com.imdb.feature.listing.domain

import com.imdb.core.base.FlowUseCase
import com.imdb.core.data.Resources
import com.imdb.core.data.ViewState
import com.imdb.core.data.safeAPICallLiveData
import com.imdb.feature.listing.data.repository.MovieListRepository
import kotlinx.coroutines.flow.map

class MovieListUseCase(private val movieListRepository: MovieListRepository) :
    FlowUseCase<Unit, ViewState>() {
    override suspend fun performAction(params: Unit?) =
        safeAPICallLiveData { movieListRepository.fetchMovies() }.map {
            when (it) {
                is Resources.Loading -> ViewState.Loading
                is Resources.Success<*> -> ViewState.Success(it.data)
                is Resources.Error -> ViewState.Error(it.error)
            }
        }

}