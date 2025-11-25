package com.woon.modernandroidvideostreamingarchitecture.domain.media

import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.repository.FavoriteRepository
import com.woon.modernandroidvideostreamingarchitecture.domain.image.model.Image
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import com.woon.modernandroidvideostreamingarchitecture.domain.media.repository.MediaRepository
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetMediaByIdUseCase
@Inject constructor(
    private val mediaRepository: MediaRepository,
    private val favoriteRepository: FavoriteRepository
){
    operator fun invoke(
        id: Long
    ): Flow<Media?> {
        return combine(
            favoriteRepository.getById(mediaId= id),
            mediaRepository.getById(mediaId = id)
        ){ favorite, media ->
            if (media == null) return@combine null
            val isFavorite = favorite != null
            when(media){
                is Video -> media.copy(favorite = isFavorite)
                is Image -> media.copy(favorite = isFavorite)
                else -> media
            }
        }
    }
}