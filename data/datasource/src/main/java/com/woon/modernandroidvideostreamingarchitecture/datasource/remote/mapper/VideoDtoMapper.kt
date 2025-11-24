package com.woon.modernandroidvideostreamingarchitecture.datasource.remote.mapper

import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.video.VideoDto
import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.video.VideoFilesDto
import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.video.VideoQualityDto
import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.video.VideoResponseDto
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.Video
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.VideoFiles
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.VideoQuality

/**
 * VideoResponseDto -> List<Media> 변환
 */
fun VideoResponseDto.toDomain(): List<Media> {
    return hits.map { it.toDomain() }
}

/**
 * VideoDto -> Video (Media) 변환
 */
fun VideoDto.toDomain(): Video {
    return Video(
        id = id,
        pageUrl = pageUrl,
        type = type,
        tags = tags.split(",").map { it.trim() },
        duration = duration,
        videos = videos.toDomain(),
        views = views,
        downloads = downloads,
        likes = likes,
        comments = comments,
        userId = userId,
        userName = user,
        userImageUrl = userImageUrl
    )
}

/**
 * VideoFilesDto -> VideoFiles 변환
 */
fun VideoFilesDto.toDomain(): VideoFiles {
    return VideoFiles(
        tiny = tiny?.toDomain(),
        small = small?.toDomain(),
        medium = medium?.toDomain(),
        large = large?.toDomain()
    )
}

/**
 * VideoQualityDto -> VideoQuality 변환
 */
fun VideoQualityDto.toDomain(): VideoQuality {
    return VideoQuality(
        url = url,
        width = width,
        height = height,
        size = size,
        thumbnail = thumbnail
    )
}
