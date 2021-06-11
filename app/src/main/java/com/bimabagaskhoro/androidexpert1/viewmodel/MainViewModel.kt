package com.bimabagaskhoro.androidexpert1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bimabagaskhoro.submission.core.domain.useCase.TvShowUseCase

class MainViewModel (tvShowUseCase: TvShowUseCase) : ViewModel() {
    val tvShow = tvShowUseCase.getListTvShows().asLiveData()
}