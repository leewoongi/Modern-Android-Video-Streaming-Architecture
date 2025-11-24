package com.woon.modernandroidvideostreamingarchitecture.domain.media

import androidx.paging.PagingData
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import com.woon.modernandroidvideostreamingarchitecture.domain.media.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMediaUseCase
@Inject constructor(
    private val mediaRepository: MediaRepository
){
    suspend operator fun invoke(
        query: String
    ) : Flow<PagingData<Media>> {
        return mediaRepository.get(query = query)
    }
}