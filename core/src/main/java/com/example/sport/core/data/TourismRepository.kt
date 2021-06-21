package com.example.sport.core.data

import com.example.sport.core.data.source.local.LocalDataSource
import com.example.sport.core.data.source.remote.RemoteDataSource
import com.example.sport.core.data.source.remote.network.ApiResponse
import com.example.sport.core.data.source.remote.response.TourismResponse
import com.example.sport.core.domain.model.Tourism
import com.example.sport.core.domain.repository.ITourismRepository
import com.example.sport.core.utils.AppExecutors
import com.example.sport.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TourismRepository(
    private val remoteDataSource: com.example.sport.core.data.source.remote.RemoteDataSource,
    private val localDataSource: com.example.sport.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors
) : ITourismRepository {

    override fun getAllTourism(): Flow<com.example.sport.core.data.Resource<List<Tourism>>> =
        object : com.example.sport.core.data.NetworkBoundResource<List<Tourism>, List<TourismResponse>>() {
            override fun loadFromDB(): Flow<List<Tourism>> {
                return localDataSource.getAllTourism().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tourism>?): Boolean =
//                data == null || data.isEmpty()
                 true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourism()

            override suspend fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }
        }.asFlow()

    override fun getFavoriteTourism(): Flow<List<Tourism>> {
        return localDataSource.getFavoriteTourism().map {
           DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
    }
}

