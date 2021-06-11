package com.bimabagaskhoro.submission.core.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    val id: Int,

    @SerializedName("poster_path")
    val avatar: String,

    @SerializedName("original_name")
    val title: String,

    @SerializedName("first_air_date")
    val date: String,

    @SerializedName("original_language")
    val original_language: String,

    @SerializedName("overview")
    val desc: String,

    @SerializedName("vote_average")
    val vote_average: Double,

    @SerializedName("backdrop_path")
    val backdrop: String
)
