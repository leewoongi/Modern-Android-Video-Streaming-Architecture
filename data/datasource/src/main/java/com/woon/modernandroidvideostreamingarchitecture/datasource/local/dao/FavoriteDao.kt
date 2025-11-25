package com.woon.modernandroidvideostreamingarchitecture.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.favorite.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE mediaId = :mediaId")
    suspend fun delete(mediaId: Long)

    @Query("SELECT * FROM favorites ORDER BY insertedAt ASC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorites WHERE mediaId = :mediaId")
    fun getById(mediaId: Long): Flow<FavoriteEntity?>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE mediaId = :mediaId)")
    fun isFavorite(mediaId: Long): Flow<Boolean>

    @Query("DELETE FROM favorites")
    suspend fun clearAll()
}
