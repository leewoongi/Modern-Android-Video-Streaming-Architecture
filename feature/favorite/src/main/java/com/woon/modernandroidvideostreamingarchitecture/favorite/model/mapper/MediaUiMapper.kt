package com.woon.modernandroidvideostreamingarchitecture.favorite.model.mapper

import com.woon.modernandroidvideostreamingarchitecture.domain.image.model.Image
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.Video
import com.woon.modernandroidvideostreamingarchitecture.favorite.model.MediaUiModel

internal fun Media.toUiModel(): MediaUiModel {
    return when (this) {
        is Video -> MediaUiModel(
            id = id,
            type = MediaType.VIDEO,
            thumbnail = videos.medium?.thumbnail
                ?: videos.small?.thumbnail
                ?: videos.large?.thumbnail
                ?: videos.tiny?.thumbnail
                ?: "",
            isFavorite = favorite,
            videoUrl = videos.medium?.url
                ?: videos.small?.url
                ?: videos.large?.url
                ?: videos.tiny?.url
        )

        is Image -> MediaUiModel(
            id = id,
            type = MediaType.IMAGE,
            thumbnail = previewURL,
            isFavorite = favorite,
            videoUrl = null
        )
        else -> {
            throw Exception("Unknown media type")
        }
    }
}