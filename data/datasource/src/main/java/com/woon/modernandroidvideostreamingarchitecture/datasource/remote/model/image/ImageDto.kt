package com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.image

import com.google.gson.annotations.SerializedName
import com.woon.modernandroidvideostreamingarchitecture.domain.image.model.Image

data class ImageDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("pageURL")
    val pageURL: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("previewURL")
    val previewURL: String,
    @SerializedName("previewWidth")
    val previewWidth: Int,
    @SerializedName("previewHeight")
    val previewHeight: Int,
    @SerializedName("webformatURL")
    val webformatURL: String,
    @SerializedName("webformatWidth")
    val webformatWidth: Int,
    @SerializedName("webformatHeight")
    val webformatHeight: Int,
    @SerializedName("largeImageURL")
    val largeImageURL: String,
    @SerializedName("imageWidth")
    val imageWidth: Int,
    @SerializedName("imageHeight")
    val imageHeight: Int,
    @SerializedName("imageSize")
    val imageSize: Int,
    @SerializedName("views")
    val views: Int,
    @SerializedName("downloads")
    val downloads: Int,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("user_id")
    val userId: Long,
    @SerializedName("user")
    val user: String,
    @SerializedName("userImageURL")
    val userImageURL: String
) {
    fun toDomain(): Image {
        return Image(
            id = id,
            pageUrl = pageURL,
            type = type,
            tags = tags.split(",").map { it.trim() },
            previewURL = previewURL,
            previewWidth = previewWidth,
            previewHeight = previewHeight,
            webformatURL = webformatURL,
            webformatWidth = webformatWidth,
            webformatHeight = webformatHeight,
            largeImageURL = largeImageURL,
            imageWidth = imageWidth,
            imageHeight = imageHeight,
            imageSize = imageSize,
            views = views,
            downloads = downloads,
            likes = likes,
            comments = comments,
            userId = userId,
            userName = user,
            userImageUrl = userImageURL,
            favorite = false
        )
    }
}