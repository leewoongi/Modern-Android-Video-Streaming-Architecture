package com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.video

import com.google.gson.annotations.SerializedName
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.VideoFiles

data class VideoFilesDto(
    @SerializedName("large")
    val large: VideoQualityDto?,
    @SerializedName("medium")
    val medium: VideoQualityDto?,
    @SerializedName("small")
    val small: VideoQualityDto?,
    @SerializedName("tiny")
    val tiny: VideoQualityDto?
) {
    fun toDomain(): VideoFiles {
        return VideoFiles(
            tiny = tiny?.toDomain(),
            small = small?.toDomain(),
            medium = medium?.toDomain(),
            large = large?.toDomain()
        )
    }
}