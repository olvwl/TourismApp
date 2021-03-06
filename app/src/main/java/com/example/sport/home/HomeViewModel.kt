package com.example.sport.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.sport.core.domain.usecase.TourismUseCase

class HomeViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val tourism = tourismUseCase.getAllTourism().asLiveData()
}

