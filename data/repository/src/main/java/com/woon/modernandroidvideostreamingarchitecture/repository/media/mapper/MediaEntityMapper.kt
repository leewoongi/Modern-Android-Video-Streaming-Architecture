package com.woon.modernandroidvideostreamingarchitecture.repository.media.mapper

import com.google.gson.Gson
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media.MediaEntity
import com.woon.modernandroidvideostreamingarchitecture.domain.image.model.Image
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.Video
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.VideoFiles

private val gson = Gson()

/**
 * Domain Media -> Entity 변환
 */
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

/**
 * Entity -> Domain Media 변환
 */
fun MediaEntity.toDomain(): Media {
    return when (mediaType) {
        "VIDEO" -> Video(
            id = id,
            pageUrl = pageUrl,
            type = mediaType,
            tags = tags.split(","),
            duration = duration ?: 0,
            videos = videoFilesJson?.let { gson.fromJson(it, VideoFiles::class.java) }
                ?: VideoFiles(null, null, null, null),
            views = views,
            downloads = downloads,
            likes = likes,
            comments = comments,
            userId = userId,
            userName = userName,
            userImageUrl = userImageUrl
        )

        "IMAGE" -> Image(
            id = id,
            pageUrl = pageUrl,
            type = mediaType,
            tags = tags.split(","),
            previewURL = previewUrl ?: "",
            previewWidth = previewWidth ?: 0,
            previewHeight = previewHeight ?: 0,
            webformatURL = webformatUrl ?: "",
            webformatWidth = webformatWidth ?: 0,
            webformatHeight = webformatHeight ?: 0,
            largeImageURL = largeImageUrl ?: "",
            imageWidth = imageWidth ?: 0,
            imageHeight = imageHeight ?: 0,
            imageSize = imageSize ?: 0,
            views = views,
            downloads = downloads,
            likes = likes,
            comments = comments,
            userId = userId,
            userName = userName,
            userImageUrl = userImageUrl
        )

        else -> throw IllegalArgumentException("Unknown media type: $mediaType")
    }
}
