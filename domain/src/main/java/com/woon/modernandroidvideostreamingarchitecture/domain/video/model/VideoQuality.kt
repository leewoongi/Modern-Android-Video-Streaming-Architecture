package com.woon.modernandroidvideostreamingarchitecture.domain.video.model

/**
 * 특정 화질의 비디오 정보
 */
data class VideoQuality(
    val url: String,
    val width: Int,
    val height: Int,
    val size: Long, // bytes
    val thumbnail: String? = null
)