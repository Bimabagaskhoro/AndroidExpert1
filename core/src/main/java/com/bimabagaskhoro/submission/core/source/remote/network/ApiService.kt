package com.bimabagaskhoro.submission.core.source.remote.network

import com.bimabagaskhoro.submission.core.BuildConfig
import com.bimabagaskhoro.submission.core.source.remote.response.ListTvShowResponse
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

    @GET("tv/popular?api_key=$API_KEY")
    suspend fun getTvShow(): ListTvShowResponse
}