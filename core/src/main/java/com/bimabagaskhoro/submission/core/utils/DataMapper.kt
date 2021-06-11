package com.bimabagaskhoro.submission.core.utils

import com.bimabagaskhoro.submission.core.domain.model.TvShow
import com.bimabagaskhoro.submission.core.source.local.entitiy.TvShowEntity
import com.bimabagaskhoro.submission.core.source.remote.response.TvShowResponse

object DataMapper {
    fun mapResponseToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                id = it.id,
                avatar = it.avatar,
                title = it.title,
                date = it.date,
                original_language = it.original_language,
                desc = it.desc,
                vote_average = it.vote_average,
                backdrop = it.backdrop,
                isFav = false
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                id = it.id,
                avatar = it.avatar,
                title = it.title,
                date = it.date,
                original_language = it.original_language,
                desc = it.desc,
                vote_average = it.vote_average,
                backdrop = it.backdrop,
                isFav = it.isFav
            )
        }

    fun mapDomainToEntity(input: TvShow) = TvShowEntity(
        id = input.id,
        avatar = input.avatar,
        title = input.title,
        date = input.date,
        original_language = input.original_language,
        desc = input.desc,
        vote_average = input.vote_average,
        backdrop = input.backdrop,
        isFav = input.isFav
    )
}