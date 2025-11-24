package com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.image

import com.google.gson.annotations.SerializedName

data class ImageResponseDto(
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int,
    @SerializedName("hits")
    val hits: List<ImageDto>
)