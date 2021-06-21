package com.example.sport.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sport.core.data.source.local.entity.TourismEntity

@Database(entities = [com.example.sport.core.data.source.local.entity.TourismEntity::class], version = 2, exportSchema = false)
abstract class TourismDatabase : RoomDatabase() {

    abstract fun tourismDao(): com.example.sport.core.data.source.local.room.TourismDao

}