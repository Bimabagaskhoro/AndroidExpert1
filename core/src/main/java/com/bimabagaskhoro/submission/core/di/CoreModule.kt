package com.bimabagaskhoro.submission.core.di

import androidx.room.Room
import com.bimabagaskhoro.submission.core.domain.repository.ITvShowRepository
import com.bimabagaskhoro.submission.core.source.TvShowRepository
import com.bimabagaskhoro.submission.core.source.local.LocalDataSource
import com.bimabagaskhoro.submission.core.source.local.room.TvShowDatabase
import com.bimabagaskhoro.submission.core.source.remote.RemoteDataSource
import com.bimabagaskhoro.submission.core.source.remote.network.ApiService
import com.bimabagaskhoro.submission.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<TvShowDatabase>().tvShowDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            TvShowDatabase::class.java, "tv_show.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITvShowRepository> {
        TvShowRepository(
            get(),
            get(),
            get()
        )
    }
}