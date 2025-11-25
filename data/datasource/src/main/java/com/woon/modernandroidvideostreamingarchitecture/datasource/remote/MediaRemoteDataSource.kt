package com.woon.modernandroidvideostreamingarchitecture.datasource.remote

import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.api.PixabayApiService
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import javax.inject.Inject

class MediaRemoteDataSource
@Inject constructor(
    private val apiService: PixabayApiService
){
    private val key = "53119360-c165fbfa569f50e91bf24f26b"

    /**
     * Video 검색
     */
    suspend fun searchVideo(query: String, page: Int = 1): List<Media> {
        val response = apiService.searchVideos(
            apiKey = key,
            query = query,
            page = page
        )
        return response.toVideoList()
    }

    /**
     * Image 검색
     */
    suspend fun searchImages(query: String, page: Int = 1): List<Media> {
        val response = apiService.searchImages(
            apiKey = key,
            query = query,
            page = page
        )
        return response.toImageList()
    }
}