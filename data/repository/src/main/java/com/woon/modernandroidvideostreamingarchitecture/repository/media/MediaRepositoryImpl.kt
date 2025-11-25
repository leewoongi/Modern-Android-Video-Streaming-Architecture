package com.woon.modernandroidvideostreamingarchitecture.repository.media

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.MediaLocalDataSource
import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.MediaRemoteDataSource
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import com.woon.modernandroidvideostreamingarchitecture.domain.media.repository.MediaRepository
import com.woon.modernandroidvideostreamingarchitecture.repository.media.remotemediator.MediaRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MediaRepositoryImpl
@Inject constructor(
    private val remoteDataSource: MediaRemoteDataSource,
    private val localDataSource: MediaLocalDataSource
): MediaRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getPagingSource(query: String): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 10,
                initialLoadSize = 3
            ),
            remoteMediator = MediaRemoteMediator(
                query = query,
                remoteDataSource = remoteDataSource,
                localDataSource = localDataSource
            ),
            pagingSourceFactory = {
                localDataSource.pagingSource(query)
            }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toDomain()
            }
        }
    }

    override fun get(query: String): Flow<List<Media>> {
        return localDataSource.get(query).map { entities ->
            entities.map { it.toDomain() }
        }
    }
}