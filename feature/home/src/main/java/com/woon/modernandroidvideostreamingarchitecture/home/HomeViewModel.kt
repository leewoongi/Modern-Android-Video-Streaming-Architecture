package com.woon.modernandroidvideostreamingarchitecture.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.woon.modernandroidvideostreamingarchitecture.domain.media.GetMediaUseCase
import com.woon.modernandroidvideostreamingarchitecture.home.intent.HomeIntent
import com.woon.modernandroidvideostreamingarchitecture.home.model.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getMediaUseCase: GetMediaUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val media = _query.flatMapLatest { query ->
        getMediaUseCase(query = query)
    }.map { pagingData ->
        pagingData.map { media ->
            media.toUiModel()
        }
    }.cachedIn(viewModelScope)

    fun processIntent(intent: HomeIntent) {
        when(intent) {
            is HomeIntent.Search -> search(intent.query)
        }
    }

    private fun search(query: String) {
        _query.value = query
    }
}