package com.woon.modernandroidvideostreamingarchitecture.favorite.model

import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType

data class MediaUiModel(
    val id: Long,
    val type: MediaType,
    val thumbnail: String,
    val isFavorite: Boolean,
    val videoUrl: String? = null
)
