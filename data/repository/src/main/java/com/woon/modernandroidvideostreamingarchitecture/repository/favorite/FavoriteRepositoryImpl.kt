package com.woon.modernandroidvideostreamingarchitecture.repository.favorite

import com.woon.modernandroidvideostreamingarchitecture.datasource.local.FavoriteLocalDataSource
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.favorite.FavoriteEntity
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.model.Favorite
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.repository.FavoriteRepository
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: FavoriteLocalDataSource
) : FavoriteRepository {

    override fun get(): Flow<List<Favorite>> {
        return localDataSource.getAllFavorites().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun add(mediaId: Long, type: MediaType) {
        val entity = FavoriteEntity(
            mediaId = mediaId,
            mediaType = type.value
        )
        localDataSource.insert(entity)
    }

    override suspend fun remove(mediaId: Long) {
        localDataSource.delete(mediaId)
    }
}
