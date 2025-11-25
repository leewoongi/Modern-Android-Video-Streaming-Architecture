package com.woon.modernandroidvideostreamingarchitecture.favorite.screen.success

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.MvsCard
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType
import com.woon.modernandroidvideostreamingarchitecture.favorite.model.MediaUiModel

@Composable
fun FavoriteMediaListScreen(
    modifier: Modifier = Modifier,
    media: List<MediaUiModel>,
    state: LazyGridState,
    onClickItem: (MediaUiModel) -> Unit,
    onFavoriteClick: (MediaUiModel) -> Unit,
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        state = state
    ) {
        items(
            count = media.size,
            key = { index ->
                val item = media[index]
                item.id
            },
        ) { index ->
            val item = media[index]
            MvsCard(
                imageUrl = item.thumbnail,
                contentDescription = "Media ${item.id}",
                isToggled = item.isFavorite,
                onIconClick = { onFavoriteClick(item) },
                onClick = { onClickItem(item) },
                isVideo = item.type == MediaType.VIDEO,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
    }
}