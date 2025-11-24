package com.woon.modernandroidvideostreamingarchitecture.datasource.remote.api

import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.image.ImageResponseDto
import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.video.VideoResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApiService {
    @GET("api/videos/")
    suspend fun searchVideos(
        @Query("key") apiKey: String = "53119360-c165fbfa569f50e91bf24f26b",
        @Query("q") query: String? = null,
        @Query("lang") lang: String = "kr",
        @Query("id") id: String? = null,
        @Query("video_type") videoType: String = "all", // "all", "film", "animation"
        @Query("category") category: String? = null,
        @Query("min_width") minWidth: Int? = null,
        @Query("min_height") minHeight: Int? = null,
        @Query("editors_choice") editorsChoice: Boolean = false,
        @Query("safesearch") safesearch: Boolean = true,
        @Query("order") order: String = "popular", // "popular", "latest"
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int = 20 // 3 - 200
    ): VideoResponseDto

    @GET("api/")
    suspend fun searchImages(
        @Query("key") apiKey: String = "53119360-c165fbfa569f50e91bf24f26b",
        @Query("q") query: String? = null,
        @Query("lang") lang: String = "en",
        @Query("id") id: String? = null,
        @Query("image_type") imageType: String = "all", // "all", "photo", "illustration", "vector"
        @Query("orientation") orientation: String = "all", // "all", "horizontal", "vertical"
        @Query("category") category: String? = null,
        @Query("min_width") minWidth: Int? = null,
        @Query("min_height") minHeight: Int? = null,
        @Query("colors") colors: String? = null,
        @Query("editors_choice") editorsChoice: Boolean = false,
        @Query("safesearch") safesearch: Boolean = true,
        @Query("order") order: String = "popular", // "popular", "latest"
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int = 20 // 3 - 200
    ): ImageResponseDto
}