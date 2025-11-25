package com.woon.modernandroidvideostreamingarchitecture.favorite.model

sealed class FavoriteUiState {
    data object Loading : FavoriteUiState()
    data class Success(val media: List<MediaUiModel>) : FavoriteUiState()
    data object Empty : FavoriteUiState()
    data class Error(val throwable: Throwable) : FavoriteUiState()
}