package com.bimabagaskhoro.submission.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: Int,

    val avatar: String,

    val title: String,

    val date: String,

    val original_language: String,

    val desc: String,

    val vote_average: Double,

    val isFav: Boolean
): Parcelable