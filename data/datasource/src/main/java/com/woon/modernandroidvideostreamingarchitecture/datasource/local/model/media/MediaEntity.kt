package com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Video와 Image를 통합 관리하는 Single Table 설계
 * RemoteMediator와 Paging3를 위한 최적화된 구조
 */
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
    val insertedAt: Long = System.currentTimeMillis()  // 삽입 순서 유지
)
