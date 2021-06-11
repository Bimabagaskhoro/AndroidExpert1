package com.bimabagaskhoro.androidexpert1.viewmodel

import androidx.lifecycle.ViewModel
import com.bimabagaskhoro.submission.core.domain.model.TvShow
import com.bimabagaskhoro.submission.core.domain.useCase.TvShowUseCase

class DetailViewModel (private val tvShowUseCase: TvShowUseCase) : ViewModel() {
    fun setTvShowFav(tvShow: TvShow, newState: Boolean) = tvShowUseCase.setFavTvShows(tvShow, newState)
}