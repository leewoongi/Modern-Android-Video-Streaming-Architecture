package com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.video

import com.google.gson.annotations.SerializedName
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media

data class VideoResponseDto(
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int,
    @SerializedName("hits")
    val hits: List<VideoDto>
) {
    fun toVideoList(): List<Media> {
        return hits.map { it.toDomain() }
    }

}
