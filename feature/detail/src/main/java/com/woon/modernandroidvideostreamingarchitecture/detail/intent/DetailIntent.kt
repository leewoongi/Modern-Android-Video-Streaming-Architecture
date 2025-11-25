package com.woon.modernandroidvideostreamingarchitecture.detail.intent

import com.woon.modernandroidvideostreamingarchitecture.detail.model.MediaUiModel

sealed class DetailIntent {
    data class OnClickFavorite(val media: MediaUiModel) : DetailIntent()
}