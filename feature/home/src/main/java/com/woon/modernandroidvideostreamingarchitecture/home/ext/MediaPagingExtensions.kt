package com.woon.modernandroidvideostreamingarchitecture.home.ext

import androidx.paging.compose.LazyPagingItems
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType
import com.woon.modernandroidvideostreamingarchitecture.home.model.MediaUiModel

/**
 * LazyPagingItems에서 첫 번째 비디오를 찾아서 반환
 */
fun LazyPagingItems<MediaUiModel>.getFirstVideo(): MediaUiModel? {
    return (0 until itemCount)
        .mapNotNull { this[it] }
        .firstOrNull { it.type == MediaType.VIDEO }
}

/**
 * LazyPagingItems에서 특정 타입의 미디어만 필터링
 */
fun LazyPagingItems<MediaUiModel>.filterByType(
    type: MediaType
): List<MediaUiModel> {
    return (0 until itemCount)
        .mapNotNull { this[it] }
        .filter { it.type == type }
}
