package com.woon.modernandroidvideostreamingarchitecture.domain.image.model

import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media

data class Image(
    override val id: Long,
    override val pageUrl: String,
    val type: String,
    override val tags: List<String>,
    val previewURL: String,
    val previewWidth: Int,
    val previewHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int,
    val webformatHeight: Int,
    val largeImageURL: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    override val views: Int,
    override val downloads: Int,
    override val likes: Int,
    override val comments: Int,
    override val userId: Long,
    override val userName: String,
    override val userImageUrl: String,
    override val favorite: Boolean
) : Media()