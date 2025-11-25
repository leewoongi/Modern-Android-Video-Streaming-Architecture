package com.woon.modernandroidvideostreamingarchitecture.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.woon.modernandroidvideostreamingarchitecture.detail.model.DetailUiState
import com.woon.modernandroidvideostreamingarchitecture.detail.sceen.error.ErrorScreen
import com.woon.modernandroidvideostreamingarchitecture.detail.sceen.loading.LoadingScreen
import com.woon.modernandroidvideostreamingarchitecture.detail.sceen.success.SuccessScreen
import com.woon.modernandroidvideostreamingarchitecture.detail.viewmodel.detailViewModel

@Composable
fun DetailScreen(
    id: Long = 0L,
    type: String = "",
){
    val viewModel = detailViewModel(id = id, type = type)
    val scrollState = rememberScrollState()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is DetailUiState.Loading -> { LoadingScreen(modifier = Modifier.fillMaxSize()) }
        is DetailUiState.Error -> {
            val error = (uiState as DetailUiState.Error).throwable
            ErrorScreen(
                modifier = Modifier
                    .fillMaxSize(),
                error = error
            )
        }
        is DetailUiState.Success -> {
            val media = (uiState as DetailUiState.Success).media
            SuccessScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                media = media,
                scrollState = scrollState
            )
        }
    }
}