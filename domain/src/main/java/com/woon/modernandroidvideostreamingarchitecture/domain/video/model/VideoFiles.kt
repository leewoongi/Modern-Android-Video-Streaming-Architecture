package com.woon.modernandroidvideostreamingarchitecture.domain.video.model

/**
 * 비디오 파일 정보
 * 다양한 화질의 비디오 URL 제공
 */
data class VideoFiles(
    val tiny: VideoQuality?,
    val small: VideoQuality?,
    val medium: VideoQuality?,
    val large: VideoQuality?
)
