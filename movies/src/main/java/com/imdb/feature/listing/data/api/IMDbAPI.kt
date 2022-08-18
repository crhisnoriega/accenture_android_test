package com.imdb.feature.listing.data.api

import com.imdb.feature.listing.data.model.MovieDetailsResponse
import com.imdb.feature.listing.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbAPI {
    @GET("en/API/MostPopularMovies/{api_key}")
    suspend fun mostPopularMovies(@Path("api_key") apiKey: String): MovieListResponse

    @GET("en/API/Title/{api_key}/{title_id}")
    suspend fun fetchMovieDetails(
        @Path("api_key") apiKey: String,
        @Path("title_id") titleId: String
    ): MovieDetailsResponse
}