package com.woon.modernandroidvideostreamingarchitecture.datasource.di

import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.MediaRemoteDataSource
import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.api.PixabayApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {
    @Provides
    @Singleton
    fun provideMediaRemoteDataSource(
        apiService: PixabayApiService
    ): MediaRemoteDataSource {
        return MediaRemoteDataSource(
            apiService = apiService
        )
    }
}