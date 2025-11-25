package com.woon.modernandroidvideostreamingarchitecture.favorite

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.GetFavoriteMediasUseCase
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.GetFavoriteUseCase
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.ToggleFavoriteUseCase
import com.woon.modernandroidvideostreamingarchitecture.favorite.intent.FavoriteIntent
import com.woon.modernandroidvideostreamingarchitecture.favorite.model.FavoriteUiState
import com.woon.modernandroidvideostreamingarchitecture.favorite.model.MediaUiModel
import com.woon.modernandroidvideostreamingarchitecture.favorite.model.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteMediasUseCase: GetFavoriteMediasUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    init {
        load()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun load() {
        viewModelScope.launch {
            _query.flatMapLatest { query ->
                getFavoriteMediasUseCase.invoke(query)
            }.map { media ->
                media.map { it.toUiModel() }
            }.catch { exception ->
                _uiState.value = FavoriteUiState.Error(exception)
            }.collect { mediaList ->
                _uiState.value = if (mediaList.isEmpty()) {
                    FavoriteUiState.Empty
                } else {
                    FavoriteUiState.Success(mediaList)
                }
            }
        }
    }

    private fun search(query: String) {
        _query.value = query
    }

    fun processIntent(intent: FavoriteIntent) {
        when (intent) {
            is FavoriteIntent.Search -> search(intent.query)
            is FavoriteIntent.OnClickFavorite -> toggleFavorite(intent.media)
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