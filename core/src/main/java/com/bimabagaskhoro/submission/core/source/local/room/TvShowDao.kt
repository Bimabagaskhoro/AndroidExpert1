package com.bimabagaskhoro.submission.core.source.local.room

import androidx.room.*
import com.bimabagaskhoro.submission.core.source.local.entitiy.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tv_show")
    fun getListTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tv_show WHERE isFav = 1")
    fun getFavTvShow(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowToFav(tvShow: List<TvShowEntity>)

    @Update
    fun updateFavTvShow(tvShow: TvShowEntity)
}