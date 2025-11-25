package com.woon.modernandroidvideostreamingarchitecture.domain.video.model

import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media

data class Video(
    override val id: Long,
    override val pageUrl: String,
    val type: String,
    override val tags: List<String>,
    val duration: Int,
    val videos: VideoFiles,
    override val views: Int,
    override val downloads: Int,
    override val likes: Int,
    override val comments: Int,
    override val userId: Long,
    override val userName: String,
    override val userImageUrl: String,
) : Media()