package com.woon.modernandroidvideostreamingarchitecture.repository.media.module

import com.woon.modernandroidvideostreamingarchitecture.domain.media.repository.MediaRepository
import com.woon.modernandroidvideostreamingarchitecture.repository.media.MediaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MediaRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMediaRepository(
        mediaRepositoryImpl: MediaRepositoryImpl
    ): MediaRepository

}