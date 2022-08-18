package com.imdb.feature.listing.di

import com.imdb.feature.listing.data.api.IMDbAPI
import com.imdb.feature.listing.data.repository.MovieDetailsRepository
import com.imdb.feature.listing.data.repository.MovieListRepository
import com.imdb.feature.listing.domain.MovieDetailsUseCase
import com.imdb.feature.listing.domain.MovieListUseCase
import com.imdb.feature.listing.presentation.viewmodel.MovieDetailsViewModel
import com.imdb.feature.listing.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeModule {
    val modules = module {
        viewModel { MovieListViewModel(get()) }
        viewModel { MovieDetailsViewModel(get()) }

        factory { MovieListUseCase(get()) }
        factory { MovieDetailsUseCase(get()) }

        factory { MovieListRepository(get()) }
        factory { MovieDetailsRepository(get()) }

        factory { providerIMDbAPI(get()) }
    }

    private fun providerIMDbAPI(retrofit: Retrofit) = retrofit.create(IMDbAPI::class.java)
}