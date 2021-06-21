package com.example.sport.core.di

import androidx.room.Room
import com.example.sport.core.data.TourismRepository
import com.example.sport.core.data.source.local.LocalDataSource
import com.example.sport.core.data.source.local.room.TourismDatabase
import com.example.sport.core.data.source.remote.RemoteDataSource
import com.example.sport.core.data.source.remote.network.ApiService
import com.example.sport.core.domain.repository.ITourismRepository
import com.example.sport.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<com.example.sport.core.data.source.local.room.TourismDatabase>().tourismDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            com.example.sport.core.data.source.local.room.TourismDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tourism-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.example.sport.core.data.source.local.LocalDataSource(get()) }
    single { com.example.sport.core.data.source.remote.RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITourismRepository> {
        com.example.sport.core.data.TourismRepository(
            get(),
            get(),
            get()
        )
    }
}