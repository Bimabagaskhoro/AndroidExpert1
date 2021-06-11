package com.bimabagaskhoro.submission.core.domain.useCase

import com.bimabagaskhoro.submission.core.domain.model.TvShow
import com.bimabagaskhoro.submission.core.domain.repository.ITvShowRepository

class TvShowInteractor(private val tvShowRepository: ITvShowRepository) : TvShowUseCase {
    override fun getListTvShows() = tvShowRepository.getListTvShow()

    override fun getFavTvShows() = tvShowRepository.getFavoriteTvShow()

    override fun setFavTvShows(tvShow: TvShow, state: Boolean) =
        tvShowRepository.setFavoriteTvShow(tvShow, state)

}