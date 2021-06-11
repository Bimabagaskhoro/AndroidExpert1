package com.bimabagaskhoro.androidexpert1.di

import com.bimabagaskhoro.submission.core.domain.useCase.TvShowInteractor
import com.bimabagaskhoro.submission.core.domain.useCase.TvShowUseCase
import com.bimabagaskhoro.androidexpert1.viewmodel.DetailViewModel
import com.bimabagaskhoro.androidexpert1.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TvShowUseCase> { TvShowInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}