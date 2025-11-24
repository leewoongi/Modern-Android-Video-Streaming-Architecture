package com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.video

import com.google.gson.annotations.SerializedName

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
)