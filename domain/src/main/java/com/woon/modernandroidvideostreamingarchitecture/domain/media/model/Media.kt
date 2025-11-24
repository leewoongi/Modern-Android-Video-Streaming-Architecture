package com.woon.modernandroidvideostreamingarchitecture.domain.media.model


abstract class Media {
    abstract val id: Long
    abstract val pageUrl: String
    abstract val tags: List<String>
    abstract val views: Int
    abstract val downloads: Int
    abstract val likes: Int
    abstract val comments: Int
    abstract val userId: Long
    abstract val userName: String
    abstract val userImageUrl: String
}
