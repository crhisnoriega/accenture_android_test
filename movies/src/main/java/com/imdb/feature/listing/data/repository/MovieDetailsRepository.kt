package com.imdb.feature.listing.data.repository

import com.imdb.feature.listing.BuildConfig
import com.imdb.feature.listing.data.api.IMDbAPI
import kotlinx.coroutines.flow.flow

class MovieDetailsRepository(private val imDbAPI: IMDbAPI) {

    suspend fun fetchMovieDetails(titleId: String) = flow {
        emit(imDbAPI.fetchMovieDetails(BuildConfig.API_KEY, titleId))
    }
}