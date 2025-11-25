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

    suspend fun insert(favorite: FavoriteEntity) {
        println("TEST TEST TEST insert favorite: $favorite")
        database.favoriteDao().insert(favorite)
    }

    suspend fun delete(mediaId: Long) {
        database.favoriteDao().delete(mediaId)
    }
}
