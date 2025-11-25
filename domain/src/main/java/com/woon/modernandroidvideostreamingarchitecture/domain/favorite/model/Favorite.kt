package com.woon.modernandroidvideostreamingarchitecture.domain.favorite.model

import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType

data class Favorite(
    val id: Long,
    val type: MediaType
)
