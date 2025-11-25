package com.woon.modernandroidvideostreamingarchitecture.domain.favorite

import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.repository.FavoriteRepository
import com.woon.modernandroidvideostreamingarchitecture.domain.image.model.Image
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import com.woon.modernandroidvideostreamingarchitecture.domain.media.repository.MediaRepository
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetFavoriteMediasUseCase
@Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val mediaRepository: MediaRepository
) {
    operator fun invoke(
        query: String
    ): Flow<List<Media>> {
        return combine(
            favoriteRepository.get(),
            mediaRepository.get(query)
        ){ favorite, media ->
            media.map { media ->
                val isFavorite = favorite.any { it.id == media.id }
                when(media){
                    is Video -> media.copy(favorite = isFavorite)
                    is Image -> media.copy(favorite = isFavorite)
                    else -> media
                }
            }.filter { it.favorite }
        }
    }
}