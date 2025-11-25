package com.woon.modernandroidvideostreamingarchitecture.detail.model

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val media: MediaUiModel) : DetailUiState()
    data class Error(val throwable: Throwable) : DetailUiState()
}