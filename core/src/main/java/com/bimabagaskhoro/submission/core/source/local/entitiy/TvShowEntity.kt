package com.bimabagaskhoro.submission.core.source.local.entitiy

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    val id: Int,

    val avatar: String,

    val title: String,

    val date: String,

    val original_language: String,

    val desc: String,

    val vote_average: Double,

    var isFav: Boolean = false
)
