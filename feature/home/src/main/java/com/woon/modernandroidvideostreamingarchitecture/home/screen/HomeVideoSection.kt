package com.woon.modernandroidvideostreamingarchitecture.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.MvsCard
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.theme.CustomTheme
import com.woon.modernandroidvideostreamingarchitecture.home.ext.getFirstVideo
import com.woon.modernandroidvideostreamingarchitecture.home.model.MediaUiModel

@Composable
fun HomeVideoSection(
    modifier: Modifier = Modifier,
    video: LazyPagingItems<MediaUiModel>,
    onClickVideo: () -> Unit,
) {
// 첫 번째 비디오 찾기
    val firstVideo = video.getFirstVideo()
    when {
        video.loadState.refresh is LoadState.Loading -> {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = CustomTheme.colors.primary)
            }
        }

        video.loadState.refresh is LoadState.Error -> {
            val error = (video.loadState.refresh as LoadState.Error).error
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error: ${error.message}",
                    color = CustomTheme.colors.error,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        firstVideo != null -> {
            Box(modifier = modifier.fillMaxWidth()) {
                MvsCard(
                    imageUrl = firstVideo.thumbnail,
                    contentDescription = "Featured Video ${firstVideo.id}",
                    isFavorite = firstVideo.isFavorite,
                    onFavoriteClick = {
//                        onFavoriteClick(firstVideo.id)
                    },
                    onClick = {
//                        onClickItem(firstVideo.id, firstVideo.type)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                // Play 아이콘
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Video",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(64.dp),
                    tint = CustomTheme.colors.onSurface.copy(alpha = 0.8f)
                )
            }
        }
    }
}