package com.example.sport.di

import com.example.sport.core.domain.usecase.TourismInteractor
import com.example.sport.core.domain.usecase.TourismUseCase
import com.example.sport.detail.DetailTourismViewModel
import com.example.sport.favorite.FavoriteViewModel
import com.example.sport.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TourismUseCase> { TourismInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailTourismViewModel(get()) }
}