package com.woon.modernandroidvideostreamingarchitecture.repository.favorite.mapper

import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.favorite.FavoriteEntity
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.model.Favorite
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType


/**
 * Domain Favorite -> FavoriteEntity 변환
 */
fun Favorite.toEntity(): FavoriteEntity {
    return FavoriteEntity(
        mediaId = id,
        mediaType = type.value
    )
}
