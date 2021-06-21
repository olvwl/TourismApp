package com.example.sport.core.data.source.local.room

import androidx.room.*
import com.example.sport.core.data.source.local.entity.TourismEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TourismDao {

    @Query("SELECT * FROM tourism")
    fun getAllTourism(): Flow<List<com.example.sport.core.data.source.local.entity.TourismEntity>>

    @Query("SELECT * FROM tourism where isFavorite = 1")
    fun getFavoriteTourism(): Flow<List<com.example.sport.core.data.source.local.entity.TourismEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTourism(tourism: List<com.example.sport.core.data.source.local.entity.TourismEntity>)

    @Update
    fun updateFavoriteTourism(tourism: com.example.sport.core.data.source.local.entity.TourismEntity)
}
