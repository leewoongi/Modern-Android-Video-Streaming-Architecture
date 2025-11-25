package com.woon.modernandroidvideostreamingarchitecture.domain.favorite.repository

import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.model.Favorite
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun get(): Flow<List<Favorite>>
    fun getById(mediaId: Long) : Flow<Favorite?>
    suspend fun add(mediaId: Long, type: MediaType)
    suspend fun remove(mediaId: Long)
}
