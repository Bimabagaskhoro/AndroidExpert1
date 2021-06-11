package com.bimabagaskhoro.submission.core.source.local

import com.bimabagaskhoro.submission.core.source.local.entitiy.TvShowEntity
import com.bimabagaskhoro.submission.core.source.local.room.TvShowDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val tvShowDao: TvShowDao) {


    fun getListTvShow(): Flow<List<TvShowEntity>> = tvShowDao.getListTvShow()

    fun getFavTvShow(): Flow<List<TvShowEntity>> = tvShowDao.getFavTvShow()

    suspend fun insertTvShow(tvShowList: List<TvShowEntity>) = tvShowDao.insertTvShowToFav(tvShowList)

    fun setFavTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFav = newState
        tvShowDao.updateFavTvShow(tvShow)
    }
}