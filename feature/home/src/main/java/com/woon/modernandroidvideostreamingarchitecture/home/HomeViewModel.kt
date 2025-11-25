package com.woon.modernandroidvideostreamingarchitecture.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.GetFavoriteUseCase
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.ToggleFavoriteUseCase
import com.woon.modernandroidvideostreamingarchitecture.domain.media.GetMediaUseCase
import com.woon.modernandroidvideostreamingarchitecture.home.intent.HomeIntent
import com.woon.modernandroidvideostreamingarchitecture.home.model.MediaUiModel
import com.woon.modernandroidvideostreamingarchitecture.home.model.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getMediaUseCase: GetMediaUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()


    @OptIn(ExperimentalCoroutinesApi::class)
    val media = _query.flatMapLatest { query ->
        getMediaUseCase.invoke(query = query)
            .cachedIn(viewModelScope)
    }.combine(
        getFavoriteUseCase.invoke()
    ){ pagingData, favorites ->
        pagingData.map { media ->
            val isFavorite = favorites.any { it.id == media.id }
            media.toUiModel(isFavorite)
        }
    }

    fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Search -> search(intent.query)
            is HomeIntent.OnClickFavorite -> toggleFavorite(intent.media)
        }
    }

    private fun search(query: String) {
        _query.value = query
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