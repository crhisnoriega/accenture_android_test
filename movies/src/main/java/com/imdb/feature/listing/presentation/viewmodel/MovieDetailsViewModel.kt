package com.imdb.feature.listing.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imdb.core.base.BaseViewModel
import com.imdb.core.data.ViewState
import com.imdb.designsystem.list.model.CardModel
import com.imdb.feature.listing.data.model.MovieDetailsResponse
import com.imdb.feature.listing.domain.MovieDetailsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieDetailsUseCase: MovieDetailsUseCase) :
    BaseViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetailsResponse>()
    val movieDetail: LiveData<MovieDetailsResponse> = _movieDetail

    fun fetMovieDetails(titleId: String) {
        viewModelScope.launch {
            movieDetailsUseCase.launch(titleId)
            movieDetailsUseCase.resultFlow.catch { }.collect {
                when (it) {
                    is ViewState.Success<*> -> _movieDetail.value = it.data as MovieDetailsResponse

                    is ViewState.Error -> {}
                }
            }
        }
    }

    fun cardModel() = CardModel(id = "", title = "", url = _movieDetail.value?.imageUrl ?: "")

}