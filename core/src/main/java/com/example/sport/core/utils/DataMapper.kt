package com.example.sport.core.utils


import com.example.sport.core.data.source.remote.response.TourismResponse
import com.example.sport.core.domain.model.Tourism

object DataMapper {
    fun mapResponsesToEntities(input: List<TourismResponse>): List<com.example.sport.core.data.source.local.entity.TourismEntity> {
        val tourismList = ArrayList<com.example.sport.core.data.source.local.entity.TourismEntity>()
        input.map {
            val tourism = com.example.sport.core.data.source.local.entity.TourismEntity(
                tourismId = it.id,
                description = it.description,
                name = it.name,
                address = it.address,
                latitude = it.latitude,
                longitude = it.longitude,
                like = it.like,
                image = it.image,
                isFavorite = false
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<com.example.sport.core.data.source.local.entity.TourismEntity>): List<Tourism> =
        input.map {
            Tourism(
                tourismId = it.tourismId,
                description = it.description,
                name = it.name,
                address = it.address,
                latitude = it.latitude,
                longitude = it.longitude,
                like = it.like,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Tourism) =
        com.example.sport.core.data.source.local.entity.TourismEntity(
            tourismId = input.tourismId,
            description = input.description,
            name = input.name,
            address = input.address,
            latitude = input.latitude,
            longitude = input.longitude,
            like = input.like,
            image = input.image,
            isFavorite = input.isFavorite
        )
}