package com.woon.modernandroidvideostreamingarchitecture.favorite.screen.success

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.woon.modernandroidvideostreamingarchitecture.favorite.model.MediaUiModel

@Composable
fun SuccessScreen(
    item: List<MediaUiModel>,
    modifier: Modifier,
    state: LazyGridState,
    onClick: (MediaUiModel) -> Unit,
    onFavoriteClick: (MediaUiModel) -> Unit,
){
    FavoriteMediaListScreen(
        modifier = modifier,
        media = item,
        state = state,
        onClickItem = { onClick(it) },
        onFavoriteClick = { onFavoriteClick(it) }
    )
}