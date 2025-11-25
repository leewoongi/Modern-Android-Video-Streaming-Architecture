package com.woon.modernandroidvideostreamingarchitecture.detail.model

import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType

data class MediaUiModel(
    val id: Long,
    val type: MediaType,
    val pageUrl: String,
    val tags: List<String>,
    val mediaUrl: String, // video URL or image URL
    val thumbnailUrl: String,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val userId: Long,
    val userName: String,
    val userImageUrl: String,
    val isFavorite: Boolean,
    val duration: Int? = null, // video only
    val width: Int? = null, // image only
    val height: Int? = null // image only
)