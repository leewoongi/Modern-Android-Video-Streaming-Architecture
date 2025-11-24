package com.woon.modernandroidvideostreamingarchitecture.datasource.remote.mapper

import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.image.ImageDto
import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.image.ImageResponseDto
import com.woon.modernandroidvideostreamingarchitecture.domain.image.model.Image
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media

/**
 * ImageResponseDto -> List<Media> 변환
 */
fun ImageResponseDto.toDomain(): List<Media> {
    return hits.map { it.toDomain() }
}

/**
 * ImageDto -> Image (Media) 변환
 */
fun ImageDto.toDomain(): Image {
    return Image(
        id = id,
        pageUrl = pageURL,
        type = type,
        tags = tags.split(",").map { it.trim() },
        previewURL = previewURL,
        previewWidth = previewWidth,
        previewHeight = previewHeight,
        webformatURL = webformatURL,
        webformatWidth = webformatWidth,
        webformatHeight = webformatHeight,
        largeImageURL = largeImageURL,
        imageWidth = imageWidth,
        imageHeight = imageHeight,
        imageSize = imageSize,
        views = views,
        downloads = downloads,
        likes = likes,
        comments = comments,
        userId = userId,
        userName = user,
        userImageUrl = userImageURL
    )
}
