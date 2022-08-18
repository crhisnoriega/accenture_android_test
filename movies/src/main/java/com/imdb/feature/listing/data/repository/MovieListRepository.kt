package com.imdb.feature.listing.data.repository

import com.imdb.feature.listing.BuildConfig
import com.imdb.feature.listing.data.api.IMDbAPI
import kotlinx.coroutines.flow.flow

class MovieListRepository(private val imDbAPI: IMDbAPI) {

    suspend fun fetchMovies() = flow {
        emit(imDbAPI.mostPopularMovies(BuildConfig.API_KEY))
    }
}