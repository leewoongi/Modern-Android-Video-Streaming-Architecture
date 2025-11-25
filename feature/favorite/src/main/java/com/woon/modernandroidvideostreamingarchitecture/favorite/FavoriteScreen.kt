package com.woon.modernandroidvideostreamingarchitecture.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.woon.modernandroidvideostreamingarchitecture.core.R
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.datacase.EmptyDataCase
import com.woon.modernandroidvideostreamingarchitecture.core.localprovider.LocalNavController
import com.woon.modernandroidvideostreamingarchitecture.favorite.intent.FavoriteIntent
import com.woon.modernandroidvideostreamingarchitecture.favorite.model.FavoriteUiState
import com.woon.modernandroidvideostreamingarchitecture.favorite.screen.error.ErrorScreen
import com.woon.modernandroidvideostreamingarchitecture.favorite.screen.loading.LoadingScreen
import com.woon.modernandroidvideostreamingarchitecture.favorite.screen.success.layout.FavoriteSearchScreen
import com.woon.modernandroidvideostreamingarchitecture.favorite.screen.success.SuccessScreen

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<FavoriteViewModel>()
    val gridState = rememberLazyGridState()
    val navController = LocalNavController.current

    val query by viewModel.query.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        FavoriteSearchScreen(
            modifier = Modifier
                .fillMaxWidth(),
            query = query,
            onSearch = { viewModel.processIntent(FavoriteIntent.Search(it)) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when(uiState){
            is FavoriteUiState.Empty -> {
                EmptyDataCase(
                    modifier = modifier,
                    message = stringResource(R.string.message_empty_favorite)
                )
            }
            is FavoriteUiState.Loading -> {
                LoadingScreen(modifier = modifier)
            }
            is FavoriteUiState.Error -> {
                val error = (uiState as FavoriteUiState.Error).throwable
                ErrorScreen(
                    modifier = modifier,
                    error = error
                )
            }
            is FavoriteUiState.Success -> {
                val media = (uiState as FavoriteUiState.Success).media
                SuccessScreen(
                    item = media,
                    modifier = Modifier.fillMaxSize(),
                    state = gridState,
                    onClickItem = { mediaItem ->
                        navController.navigate("detail/${mediaItem.id}/${mediaItem.type}")
                    },
                    onFavoriteClick = { viewModel.processIntent(FavoriteIntent.OnClickFavorite(it)) }
                )
            }
        }
    }
}