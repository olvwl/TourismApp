package com.example.sport.core.data.source.local

import com.example.sport.core.data.source.local.entity.TourismEntity
import com.example.sport.core.data.source.local.room.TourismDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val tourismDao: com.example.sport.core.data.source.local.room.TourismDao) {

    fun getAllTourism(): Flow<List<com.example.sport.core.data.source.local.entity.TourismEntity>> = tourismDao.getAllTourism()

    fun getFavoriteTourism(): Flow<List<com.example.sport.core.data.source.local.entity.TourismEntity>> = tourismDao.getFavoriteTourism()

    suspend fun insertTourism(tourismList: List<com.example.sport.core.data.source.local.entity.TourismEntity>) = tourismDao.insertTourism(tourismList)

    fun setFavoriteTourism(tourism: com.example.sport.core.data.source.local.entity.TourismEntity, newState: Boolean) {
        tourism.isFavorite = newState
        tourismDao.updateFavoriteTourism(tourism)
    }
}