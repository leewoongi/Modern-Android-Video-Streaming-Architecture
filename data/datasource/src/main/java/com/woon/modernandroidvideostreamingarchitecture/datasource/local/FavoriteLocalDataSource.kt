package com.woon.modernandroidvideostreamingarchitecture.datasource.local

import com.woon.modernandroidvideostreamingarchitecture.datasource.local.database.AppDatabase
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.favorite.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteLocalDataSource @Inject constructor(
    private val database: AppDatabase
) {
    fun getAllFavorites(): Flow<List<FavoriteEntity>> {
        return database.favoriteDao().getAllFavorites()
    }

    fun getById(mediaId: Long): Flow<FavoriteEntity?> {
        return database.favoriteDao().getById(mediaId)
    }

    suspend fun insert(favorite: FavoriteEntity) {
        database.favoriteDao().insert(favorite)
    }

    suspend fun delete(mediaId: Long) {
        database.favoriteDao().delete(mediaId)
    }
}
