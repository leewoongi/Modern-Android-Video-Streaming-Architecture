package com.woon.modernandroidvideostreamingarchitecture.home.intent

import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType
import com.woon.modernandroidvideostreamingarchitecture.home.model.MediaUiModel

sealed class HomeIntent {
    data class Search(val query: String) : HomeIntent()
    data class OnClickFavorite(val media: MediaUiModel) : HomeIntent()
}