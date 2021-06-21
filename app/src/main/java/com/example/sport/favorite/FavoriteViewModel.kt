package com.example.sport.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.sport.core.domain.usecase.TourismUseCase

class FavoriteViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val favoriteTourism = tourismUseCase.getFavoriteTourism().asLiveData()
}

