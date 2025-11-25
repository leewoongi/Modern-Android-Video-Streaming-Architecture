package com.woon.modernandroidvideostreamingarchitecture.domain.favorite

import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.repository.FavoriteRepository
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType
import javax.inject.Inject

class ToggleFavoriteUseCase
@Inject constructor(
    private val favoriteRepository: FavoriteRepository
){
    suspend operator fun invoke(
        mediaId: Long,
        mediaType: MediaType,
        isFavorite: Boolean
    ) {
        if(isFavorite) {
            favoriteRepository.remove(mediaId)
        } else {
            favoriteRepository.add(mediaId, mediaType)
        }
    }
}