package com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.video

import com.google.gson.annotations.SerializedName
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.Video

/**
 * Pixabay API 비디오 응답 모델
 */

data class VideoDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("pageURL")
    val pageUrl: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("tags")
    val tags: String, // comma separated
    @SerializedName("duration")
    val duration: Int,

    // 비디오 파일 정보
    @SerializedName("videos")
    val videos: VideoFilesDto,

    @SerializedName("views")
    val views: Int,
    @SerializedName("downloads")
    val downloads: Int,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("comments")
    val comments: Int,

    // 사용자 정보
    @SerializedName("user_id")
    val userId: Long,
    @SerializedName("user")
    val user: String,
    @SerializedName("userImageURL")
    val userImageUrl: String
) {
    fun toDomain(): Video {
        return Video(
            id = id,
            pageUrl = pageUrl,
            type = type,
            tags = tags.split(",").map { it.trim() },
            duration = duration,
            videos = videos.toDomain(),
            views = views,
            downloads = downloads,
            likes = likes,
            comments = comments,
            userId = userId,
            userName = user,
            userImageUrl = userImageUrl,
            favorite = false
        )
    }
}
