package com.bangkit.tvshow.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bimabagaskhoro.submission.core.domain.useCase.TvShowUseCase

class FavoriteViewModel (tvShowUseCase: TvShowUseCase) : ViewModel() {
    val favTvShow= tvShowUseCase.getFavTvShows().asLiveData()
}