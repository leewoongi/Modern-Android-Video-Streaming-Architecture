package com.woon.modernandroidvideostreamingarchitecture.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woon.modernandroidvideostreamingarchitecture.detail.intent.DetailIntent
import com.woon.modernandroidvideostreamingarchitecture.detail.model.DetailUiState
import com.woon.modernandroidvideostreamingarchitecture.detail.model.MediaUiModel
import com.woon.modernandroidvideostreamingarchitecture.detail.model.mapper.toUiModel
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.ToggleFavoriteUseCase
import com.woon.modernandroidvideostreamingarchitecture.domain.media.GetMediaByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailViewModel
@AssistedInject constructor(
    @Assisted private val id: Long,
    @Assisted private val type: String,
    private val getMediaByIdUseCase: GetMediaByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        load()
    }

    private fun load() {
        _uiState.value = DetailUiState.Loading
        viewModelScope.launch {
            getMediaByIdUseCase.invoke(id = id).collect { media ->
                _uiState.value = if (media != null) {
                    DetailUiState.Success(media.toUiModel())
                } else {
                    DetailUiState.Error(IllegalStateException("Media not found"))
                }
            }
        }
    }

    fun processIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.OnClickFavorite -> toggleFavorite(intent.media)
        }
    }

    private fun toggleFavorite(
        model: MediaUiModel,
    ) {
        viewModelScope.launch {
            toggleFavoriteUseCase.invoke(
                mediaId = model.id,
                mediaType = model.type,
                isFavorite = model.isFavorite
            )
        }
    }
}