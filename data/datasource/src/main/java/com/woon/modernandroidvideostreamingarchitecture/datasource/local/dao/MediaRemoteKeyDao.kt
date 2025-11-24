package com.woon.modernandroidvideostreamingarchitecture.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media.MediaRemoteKey

@Dao
interface MediaRemoteKeyDao {

    /**
     * RemoteKey 삽입
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKey: MediaRemoteKey)

    /**
     * 특정 쿼리의 RemoteKey 조회
     */
    @Query("SELECT * FROM media_key WHERE `query` = :query")
    suspend fun getRemoteKeyByQuery(query: String): MediaRemoteKey?

    /**
     * 특정 쿼리의 RemoteKey 삭제
     */
    @Query("DELETE FROM media_key WHERE `query` = :query")
    suspend fun clearByQuery(query: String)

    /**
     * 모든 RemoteKey 삭제
     */
    @Query("DELETE FROM media_key")
    suspend fun clearAll()
}
