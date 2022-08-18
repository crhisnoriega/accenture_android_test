package com.imdb.feature.listing.domain

import com.imdb.feature.listing.data.repository.MovieListRepository

class MovieListUseCase(private val movieListRepository: MovieListRepository) {

    suspend fun fetchMovies() = movieListRepository.fetchMovies()
}