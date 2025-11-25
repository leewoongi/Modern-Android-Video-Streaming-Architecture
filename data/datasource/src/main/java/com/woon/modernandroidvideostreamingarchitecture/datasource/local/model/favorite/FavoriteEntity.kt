package com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.model.Favorite
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType

/**
 * 사용자가 즐겨찾기한 미디어를 관리하는 Entity
 */
@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val mediaId: Long,
    val mediaType: String,          // "VIDEO" or "IMAGE"
    val insertedAt: Long = System.currentTimeMillis()
) {
    fun toDomain() : Favorite {
        return Favorite(
            id = mediaId,
            type = MediaType.fromValue(mediaType)
        )
    }
}
