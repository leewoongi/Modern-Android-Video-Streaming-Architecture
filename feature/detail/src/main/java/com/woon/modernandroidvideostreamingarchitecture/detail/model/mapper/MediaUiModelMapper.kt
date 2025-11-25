package com.woon.modernandroidvideostreamingarchitecture.detail.model.mapper

import com.woon.modernandroidvideostreamingarchitecture.detail.model.MediaUiModel
import com.woon.modernandroidvideostreamingarchitecture.domain.image.model.Image
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.Video

fun Media.toUiModel(): MediaUiModel {
    return when (this) {
        is Video -> this.toUiModel()
        is Image -> this.toUiModel()
        else -> throw IllegalArgumentException("Unknown media type")
    }
}

fun Video.toUiModel(): MediaUiModel {

    return MediaUiModel(
        id = id,
        type = MediaType.VIDEO,
        pageUrl = pageUrl,
        tags = tags,
        thumbnailUrl = this.videos.large?.thumbnail
            ?: this.videos.medium?.thumbnail
            ?: this.videos.small?.thumbnail
            ?: this.videos.tiny?.thumbnail
            ?: "",
        mediaUrl = this.videos.large?.url
            ?: this.videos.medium?.url
            ?: this.videos.small?.url
            ?: this.videos.tiny?.url
            ?:"",
        views = views,
        downloads = downloads,
        likes = likes,
        comments = comments,
        userId = userId,
        userName = userName,
        userImageUrl = userImageUrl,
        isFavorite = favorite,
        duration = duration,
        width = videos.medium?.width,
        height = videos.medium?.height
    )
}

fun Image.toUiModel(): MediaUiModel {
    return MediaUiModel(
        id = id,
        type = MediaType.IMAGE,
        pageUrl = pageUrl,
        tags = tags,
        mediaUrl = largeImageURL,
        thumbnailUrl = previewURL,
        views = views,
        downloads = downloads,
        likes = likes,
        comments = comments,
        userId = userId,
        userName = userName,
        userImageUrl = userImageUrl,
        isFavorite = favorite,
        duration = null,
        width = imageWidth,
        height = imageHeight
    )
}