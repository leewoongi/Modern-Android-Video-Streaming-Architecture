package com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_key")
data class MediaRemoteKey(
    @PrimaryKey val query: String,
    val prevPage: Int?,
    val nextPage: Int?
)
