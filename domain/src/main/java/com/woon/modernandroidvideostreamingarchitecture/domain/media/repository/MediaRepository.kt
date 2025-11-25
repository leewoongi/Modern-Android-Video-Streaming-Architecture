package com.woon.modernandroidvideostreamingarchitecture.domain.media.repository

import androidx.paging.PagingData
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    suspend fun getPagingSource(query: String) : Flow<PagingData<Media>>
    fun get(query: String): Flow<List<Media>>
    fun getById(mediaId: Long): Flow<Media?>
}