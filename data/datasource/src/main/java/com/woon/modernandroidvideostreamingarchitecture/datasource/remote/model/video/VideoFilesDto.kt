package com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.video

import com.google.gson.annotations.SerializedName

data class VideoFilesDto(
    @SerializedName("large")
    val large: VideoQualityDto?,
    @SerializedName("medium")
    val medium: VideoQualityDto?,
    @SerializedName("small")
    val small: VideoQualityDto?,
    @SerializedName("tiny")
    val tiny: VideoQualityDto?
)