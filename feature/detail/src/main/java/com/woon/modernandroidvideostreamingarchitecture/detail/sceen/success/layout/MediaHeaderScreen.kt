package com.woon.modernandroidvideostreamingarchitecture.detail.sceen.success.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.MvsToggleIconButton

@Composable
fun MediaHeaderScreen(
    modifier: Modifier,
    content: @Composable () -> Unit,
    isToggled: Boolean = false,
    onIconClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
){
    Box(
        modifier = modifier
            .aspectRatio(16f / 9f)
    ) {
        content()

        // 즐겨찾기 버튼
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { onFavoriteClick() },
            contentAlignment = Alignment.Center
        ) {
            MvsToggleIconButton(
                isSelected = isToggled,
                onSelectionChange = { onIconClick() },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}