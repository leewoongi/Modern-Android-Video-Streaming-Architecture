package com.woon.modernandroidvideostreamingarchitecture.favorite.screen.success

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.woon.modernandroidvideostreamingarchitecture.favorite.model.MediaUiModel
import com.woon.modernandroidvideostreamingarchitecture.favorite.screen.success.layout.FavoriteMediaListScreen

@Composable
fun SuccessScreen(
    item: List<MediaUiModel>,
    modifier: Modifier,
    state: LazyGridState,
    onClickItem: (MediaUiModel) -> Unit,
    onFavoriteClick: (MediaUiModel) -> Unit,
){
    FavoriteMediaListScreen(
        modifier = modifier,
        media = item,
        state = state,
        onClickItem = { onClickItem(it) },
        onFavoriteClick = { onFavoriteClick(it) }
    )
}