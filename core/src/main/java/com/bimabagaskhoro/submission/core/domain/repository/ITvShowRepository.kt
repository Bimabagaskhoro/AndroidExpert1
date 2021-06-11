package com.bimabagaskhoro.submission.core.domain.repository

import com.bimabagaskhoro.submission.core.source.Resource
import com.bimabagaskhoro.submission.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {

    fun getListTvShow(): Flow<Resource<List<TvShow>>>

    fun getFavoriteTvShow(): Flow<List<TvShow>>

    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
}