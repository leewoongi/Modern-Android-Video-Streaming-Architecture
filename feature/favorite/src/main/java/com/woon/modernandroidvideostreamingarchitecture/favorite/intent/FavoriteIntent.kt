package com.woon.modernandroidvideostreamingarchitecture.favorite.intent

import com.woon.modernandroidvideostreamingarchitecture.favorite.model.MediaUiModel

sealed class FavoriteIntent {
    data class Search(val query: String) : FavoriteIntent()
    data class OnClickFavorite(val media: MediaUiModel) : FavoriteIntent()
}