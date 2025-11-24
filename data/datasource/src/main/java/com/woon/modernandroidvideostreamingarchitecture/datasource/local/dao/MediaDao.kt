package com.woon.modernandroidvideostreamingarchitecture.datasource.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media.MediaEntity

@Dao
interface MediaDao {

    /**
     * PagingSource for Paging3
     */
    @Query("SELECT * FROM media WHERE `query` = :query ORDER BY insertedAt ASC")
    fun pagingSource(query: String): PagingSource<Int, MediaEntity>

    /**
     * Insert media list (RemoteMediator에서 사용)
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mediaList: List<MediaEntity>)

    /**
     * 특정 쿼리의 모든 미디어 삭제 (새로고침 시)
     */
    @Query("DELETE FROM media WHERE `query` = :query")
    suspend fun clearByQuery(query: String)

    /**
     * 모든 미디어 삭제
     */
    @Query("DELETE FROM media")
    suspend fun clearAll()

    /**
     * 특정 ID의 미디어 조회
     */
    @Query("SELECT * FROM media WHERE id = :mediaId")
    suspend fun getById(mediaId: Long): MediaEntity?
}