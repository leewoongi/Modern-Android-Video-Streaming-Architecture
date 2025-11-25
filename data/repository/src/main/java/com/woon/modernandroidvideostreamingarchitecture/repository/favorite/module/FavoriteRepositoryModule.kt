package com.woon.modernandroidvideostreamingarchitecture.repository.favorite.module

import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.repository.FavoriteRepository
import com.woon.modernandroidvideostreamingarchitecture.repository.favorite.FavoriteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(
        favoriteRepositoryImpl: FavoriteRepositoryImpl
    ): FavoriteRepository
}
