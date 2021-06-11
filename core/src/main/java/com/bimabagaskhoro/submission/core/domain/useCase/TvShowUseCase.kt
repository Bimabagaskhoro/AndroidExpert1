package com.bimabagaskhoro.submission.core.domain.useCase

import com.bimabagaskhoro.submission.core.source.Resource
import com.bimabagaskhoro.submission.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {
    fun getListTvShows(): Flow<Resource<List<TvShow>>>

    fun getFavTvShows(): Flow<List<TvShow>>

    fun setFavTvShows(tvShow: TvShow, state: Boolean)
}