package com.woon.modernandroidvideostreamingarchitecture.repository.media.remotemediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.MediaLocalDataSource
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media.MediaEntity
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media.MediaRemoteKey
import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.MediaRemoteDataSource
import com.woon.modernandroidvideostreamingarchitecture.repository.media.mapper.toEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class MediaRemoteMediator @Inject constructor(
    private val query: String,
    private val remoteDataSource: MediaRemoteDataSource,
    private val localDataSource: MediaLocalDataSource
) : RemoteMediator<Int, MediaEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MediaEntity>,
    ): MediatorResult {
        return try {
            // 1. 로드할 페이지 결정
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = localDataSource.getRemoteKeyByQuery(query)
                    remoteKey?.nextPage?.minus(1) ?: STARTING_PAGE_INDEX
                }
                LoadType.PREPEND -> {
                    val remoteKey = localDataSource.getRemoteKeyByQuery(query)
                    val prevPage = remoteKey?.prevPage
                    prevPage ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val remoteKey = localDataSource.getRemoteKeyByQuery(query)
                    val nextPage = remoteKey?.nextPage
                    nextPage ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            // 2. 서버에서 데이터 가져오기 (Video + Image 합치기)
            val videos = remoteDataSource.searchVideo(query = query, page = page)
            val images = remoteDataSource.searchImages(query = query, page = page)
            val mediaList = videos + images
            val endOfPaginationReached = mediaList.isEmpty()

            // 3. RemoteKey 생성
            val prevPage = if (page == STARTING_PAGE_INDEX) null else page - 1
            val nextPage = if (endOfPaginationReached) null else page + 1
            val remoteKey = MediaRemoteKey(
                query = query,
                prevPage = prevPage,
                nextPage = nextPage
            )

            // 4. Entity로 변환
            val entities = mediaList.map { it.toEntity(query, page) }

            // 5. DB에 트랜잭션으로 저장 (LocalDataSource에 위임)
            localDataSource.saveMediaWithTransaction(
                query = query,
                mediaList = entities,
                remoteKey = remoteKey,
                shouldClearExisting = loadType == LoadType.REFRESH
            )

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}