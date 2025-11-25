package com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.video

import com.google.gson.annotations.SerializedName
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.VideoQuality

data class VideoQualityDto(
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("size")
    val size: Long,
    @SerializedName("thumbnail")
    val thumbnail: String?
) {
    fun toDomain(): VideoQuality {
        return VideoQuality(
            url = url,
            width = width,
            height = height,
            size = size,
            thumbnail = thumbnail
        )
    }
}