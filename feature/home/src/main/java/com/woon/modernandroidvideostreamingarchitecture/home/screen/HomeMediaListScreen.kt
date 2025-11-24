package com.woon.modernandroidvideostreamingarchitecture.home.screen

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.MvsCard
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.datacase.EmptyDataCase
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.datacase.ErrorDataCase
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.datacase.LoadingDataCase
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.paging.PagingAppendStateFooter
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.paging.PagingRefreshStateItem
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType
import com.woon.modernandroidvideostreamingarchitecture.home.model.MediaUiModel
import kotlin.text.get

private const val VIDEO_ASPECT_RATIO = 16f / 9f
private const val IMAGE_ASPECT_RATIO = 1f

@Composable
fun HomeMediaListScreen(
    modifier: Modifier = Modifier,
    media: LazyPagingItems<MediaUiModel>,
    state: LazyGridState,
    onClickItem: (Long, MediaType) -> Unit = { _, _ -> },
    onFavoriteClick: (Long) -> Unit = {}
) {
    PagingRefreshStateItem(
        items = media,
        loadingContent = { LoadingDataCase() },
        errorContent = { error -> ErrorDataCase(error = error) },
        emptyContent = { EmptyDataCase(message = "데이터가 없습니다.") },
        successContent = { items ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                state = state
            ) {
                items(
                    count = items.itemCount,
                    key = items.itemKey { it.id },
                    span = { index ->
                        val item = items[index]
                        if (item?.type == MediaType.VIDEO) {
                            GridItemSpan(2)
                        } else {
                            GridItemSpan(1)
                        }
                    },
                    contentType = items.itemContentType {
                        when(it.type) {
                            MediaType.VIDEO -> "video"
                            MediaType.IMAGE -> "image"
                        }
                    }
                ) { index ->
                    val item = items[index]
                    if (item != null) {
                        MvsCard(
                            imageUrl = item.thumbnail,
                            contentDescription = "Media ${item.id}",
                            isFavorite = item.isFavorite,
                            onFavoriteClick = { onFavoriteClick(item.id) },
                            onClick = { onClickItem(item.id, item.type) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(if (item.type == MediaType.VIDEO) VIDEO_ASPECT_RATIO else IMAGE_ASPECT_RATIO)
                        )
                    }
                }

                // Append State Footer
                item(
                    key = "append_footer",
                    span = { GridItemSpan(2) }
                ) {
                    PagingAppendStateFooter(
                        items = items,
                        loadingContent = { LoadingDataCase() },
                        errorContent = { error -> ErrorDataCase(error = error) }
                    )
                }
            }
        }
    )
}
