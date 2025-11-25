package com.woon.modernandroidvideostreamingarchitecture.domain.media

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.GetFavoriteUseCase
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.repository.FavoriteRepository
import com.woon.modernandroidvideostreamingarchitecture.domain.image.model.Image
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import com.woon.modernandroidvideostreamingarchitecture.domain.media.repository.MediaRepository
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMediaUseCase
@Inject constructor(
    private val mediaRepository: MediaRepository
){
    suspend operator fun invoke(
        query: String
    ) : Flow<PagingData<Media>> {
        return mediaRepository.getPagingSource(query = query)
    }
}