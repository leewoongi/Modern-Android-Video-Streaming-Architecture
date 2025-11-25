package com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.image

import com.google.gson.annotations.SerializedName
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media

data class ImageResponseDto(
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int,
    @SerializedName("hits")
    val hits: List<ImageDto>
) {
    fun toImageList(): List<Media> {
        return hits.map { it.toDomain() }
    }
}