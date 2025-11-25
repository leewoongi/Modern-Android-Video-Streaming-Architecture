package com.woon.modernandroidvideostreamingarchitecture.repository.media.mapper

import com.google.gson.Gson
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media.MediaEntity
import com.woon.modernandroidvideostreamingarchitecture.domain.image.model.Image
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.Video
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.VideoFiles


/**
 * Domain Media -> Entity 변환
 */
private val gson = Gson()
fun Media.toEntity(query: String, page: Int): MediaEntity {
    return when (this) {
        is Video -> MediaEntity(
            id = id,
            query = query,
            mediaType = "VIDEO",
            pageUrl = pageUrl,
            tags = tags.joinToString(","),
            views = views,
            downloads = downloads,
            likes = likes,
            comments = comments,
            userId = userId,
            userName = userName,
            userImageUrl = userImageUrl,
            // Video 전용 필드
            duration = duration,
            videoFilesJson = gson.toJson(videos),
            // Image 필드는 null
            previewUrl = null,
            previewWidth = null,
            previewHeight = null,
            webformatUrl = null,
            webformatWidth = null,
            webformatHeight = null,
            largeImageUrl = null,
            imageWidth = null,
            imageHeight = null,
            imageSize = null,
            // 메타데이터
            page = page
        )

        is Image -> MediaEntity(
            id = id,
            query = query,
            mediaType = "IMAGE",
            pageUrl = pageUrl,
            tags = tags.joinToString(","),
            views = views,
            downloads = downloads,
            likes = likes,
            comments = comments,
            userId = userId,
            userName = userName,
            userImageUrl = userImageUrl,
            // Video 필드는 null
            duration = null,
            videoFilesJson = null,
            // Image 전용 필드
            previewUrl = previewURL,
            previewWidth = previewWidth,
            previewHeight = previewHeight,
            webformatUrl = webformatURL,
            webformatWidth = webformatWidth,
            webformatHeight = webformatHeight,
            largeImageUrl = largeImageURL,
            imageWidth = imageWidth,
            imageHeight = imageHeight,
            imageSize = imageSize,
            // 메타데이터
            page = page
        )

        else -> throw IllegalArgumentException("Unknown media type: ${this}")
    }
}
