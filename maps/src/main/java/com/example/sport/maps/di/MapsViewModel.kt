package com.example.sport.maps.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.sport.core.domain.usecase.TourismUseCase

class MapsViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val tourism = tourismUseCase.getAllTourism().asLiveData()
}

