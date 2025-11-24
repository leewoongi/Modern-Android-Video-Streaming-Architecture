package com.woon.modernandroidvideostreamingarchitecture.domain.media.repository

import androidx.paging.PagingData
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    suspend fun get(query: String) : Flow<PagingData<Media>>
}