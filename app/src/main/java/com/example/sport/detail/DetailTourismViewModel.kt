package com.example.sport.detail

import androidx.lifecycle.ViewModel
import com.example.sport.core.domain.model.Tourism
import com.example.sport.core.domain.usecase.TourismUseCase

class DetailTourismViewModel(private val tourismUseCase: TourismUseCase) : ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus:Boolean) =
        tourismUseCase.setFavoriteTourism(tourism, newStatus)
}

