package com.woon.modernandroidvideostreamingarchitecture.datasource.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.database.AppDatabase
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media.MediaEntity
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media.MediaRemoteKey
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MediaLocalDataSource
@Inject constructor(
    private val database: AppDatabase,
) {
    private val mediaDao = database.mediaDao()
    private val remoteKeyDao = database.mediaRemoteKeysDao()

    /**
     * PagingSource 제공
     */
    fun pagingSource(query: String): PagingSource<Int, MediaEntity> {
        return mediaDao.pagingSource(query)
    }

    /**
     * flow
     */
    fun get(query: String) : Flow<List<MediaEntity>> {
        return mediaDao.get(query)
    }

    /**
     * Media 삽입
     */
    suspend fun insertAll(mediaList: List<MediaEntity>) {
        mediaDao.insertAll(mediaList)
    }

    /**
     * 특정 쿼리의 Media 삭제
     */
    suspend fun clearByQuery(query: String) {
        mediaDao.clearByQuery(query)
    }

    /**
     * 모든 Media 삭제
     */
    suspend fun clearAll() {
        mediaDao.clearAll()
    }

    /**
     * ID로 Media 조회
     */
    fun getById(mediaId: Long): Flow<MediaEntity?> {
        return mediaDao.getById(mediaId)
    }

    // ========== RemoteKey 관련 ==========

    /**
     * RemoteKey 삽입
     */
    suspend fun insertRemoteKey(remoteKey: MediaRemoteKey) {
        remoteKeyDao.insert(remoteKey)
    }

    /**
     * RemoteKey 조회
     */
    suspend fun getRemoteKeyByQuery(query: String): MediaRemoteKey? {
        return remoteKeyDao.getRemoteKeyByQuery(query)
    }

    /**
     * 특정 쿼리의 RemoteKey 삭제
     */
    suspend fun clearRemoteKeyByQuery(query: String) {
        remoteKeyDao.clearByQuery(query)
    }

    /**
     * 모든 RemoteKey 삭제
     */
    suspend fun clearAllRemoteKeys() {
        remoteKeyDao.clearAll()
    }

    // ========== Transaction 캡슐화 ==========

    /**
     * RemoteMediator를 위한 트랜잭션 저장
     * REFRESH 시 기존 데이터 삭제 + RemoteKey 업데이트 + 새 데이터 삽입을 원자적으로 수행
     */
    suspend fun saveMediaWithTransaction(
        query: String,
        mediaList: List<MediaEntity>,
        remoteKey: MediaRemoteKey,
        shouldClearExisting: Boolean
    ) {
        database.withTransaction {
            if (shouldClearExisting) {
                clearAll()
                clearAllRemoteKeys()
            }
            insertRemoteKey(remoteKey)
            insertAll(mediaList)
        }
    }
}