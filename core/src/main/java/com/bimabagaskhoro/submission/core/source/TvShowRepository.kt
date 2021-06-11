package com.bimabagaskhoro.submission.core.source

import com.bimabagaskhoro.submission.core.domain.model.TvShow
import com.bimabagaskhoro.submission.core.domain.repository.ITvShowRepository
import com.bimabagaskhoro.submission.core.source.local.LocalDataSource
import com.bimabagaskhoro.submission.core.source.remote.RemoteDataSource
import com.bimabagaskhoro.submission.core.source.remote.network.ApiResponse
import com.bimabagaskhoro.submission.core.source.remote.response.TvShowResponse
import com.bimabagaskhoro.submission.core.utils.AppExecutors
import com.bimabagaskhoro.submission.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TvShowRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITvShowRepository {


    override fun getListTvShow(): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getListTvShow().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getListTvShow()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertTvShow(tvShowList)
            }
        }.asFlow()

    override fun getFavoriteTvShow(): Flow<List<TvShow>> {
        return localDataSource.getFavTvShow().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) {
        val tvShowsEntity = DataMapper.mapDomainToEntity(tvShow)
        appExecutors.diskIO().execute {
            localDataSource.setFavTvShow(tvShowsEntity, state)
        }
    }


}