package com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.woon.modernandroidvideostreamingarchitecture.domain.image.model.Image
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.Media
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.Video
import com.woon.modernandroidvideostreamingarchitecture.domain.video.model.VideoFiles

/**
 * Video와 Image를 통합 관리하는 Single Table 설계
 * RemoteMediator와 Paging3를 위한 최적화된 구조
 */
private val gson = Gson()

@Entity(tableName = "media")
data class MediaEntity(
    @PrimaryKey
    val id: Long,

    // ========== 공통 필드 ==========
    val query: String,              // 검색 쿼리 (페이징 키)
    val mediaType: String,          // "VIDEO" or "IMAGE" (구분자)
    val pageUrl: String,
    val tags: String,               // comma-separated
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val userId: Long,
    val userName: String,
    val userImageUrl: String,

    // ========== Video 전용 필드 (Image일 때 null) ==========
    val duration: Int? = null,
    val videoFilesJson: String? = null,  // VideoFiles를 JSON으로 직렬화

    // ========== Image 전용 필드 (Video일 때 null) ==========
    val previewUrl: String? = null,
    val previewWidth: Int? = null,
    val previewHeight: Int? = null,
    val webformatUrl: String? = null,
    val webformatWidth: Int? = null,
    val webformatHeight: Int? = null,
    val largeImageUrl: String? = null,
    val imageWidth: Int? = null,
    val imageHeight: Int? = null,
    val imageSize: Int? = null,

    // ========== RemoteMediator 메타데이터 ==========
    val page: Int,                      // 현재 페이지 번호
    val insertedAt: Long = System.currentTimeMillis(),  // 삽입 순서 유지
) {
    fun toDomain(): Media {
        return when (mediaType) {
            "VIDEO" -> Video(
                id = id,
                pageUrl = pageUrl,
                type = mediaType,
                tags = tags.split(","),
                duration = duration ?: 0,
                videos = videoFilesJson?.let { gson.fromJson(it, VideoFiles::class.java) }
                    ?: VideoFiles(null, null, null, null),
                views = views,
                downloads = downloads,
                likes = likes,
                comments = comments,
                userId = userId,
                userName = userName,
                userImageUrl = userImageUrl,
                favorite = false
            )

            "IMAGE" -> Image(
                id = id,
                pageUrl = pageUrl,
                type = mediaType,
                tags = tags.split(","),
                previewURL = previewUrl ?: "",
                previewWidth = previewWidth ?: 0,
                previewHeight = previewHeight ?: 0,
                webformatURL = webformatUrl ?: "",
                webformatWidth = webformatWidth ?: 0,
                webformatHeight = webformatHeight ?: 0,
                largeImageURL = largeImageUrl ?: "",
                imageWidth = imageWidth ?: 0,
                imageHeight = imageHeight ?: 0,
                imageSize = imageSize ?: 0,
                views = views,
                downloads = downloads,
                likes = likes,
                comments = comments,
                userId = userId,
                userName = userName,
                userImageUrl = userImageUrl,
                favorite = false
            )

            else -> throw IllegalArgumentException("Unknown media type: $mediaType")
        }
    }
}
