package com.woon.modernandroidvideostreamingarchitecture.core.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.woon.modernandroidvideostreamingarchitecture.core.R
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.theme.CustomTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MvsCard(
    imageUrl: String,
    contentDescription: String? = null,
    isToggled: Boolean = false,
    onIconClick: () -> Unit = {},
    onClick: () -> Unit = {},
    showCheckIcon: Boolean = false,
    iconAlignment: Alignment = Alignment.BottomEnd,
    iconPadding: Dp = 12.dp,
    isVideo: Boolean = false,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = CustomTheme.colors.surface)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            GlideImage(
                model = imageUrl,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                loading = placeholder(R.drawable.placeholder_image),
                failure = placeholder(R.drawable.error_image)
            )

            // Video 타입이면 가운데에 플레이 버튼 표시
            if (isVideo) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(56.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.6f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }

            MvsToggleIconButton(
                isSelected = isToggled,
                onSelectionChange = { onIconClick() },
                showCheckIcon = showCheckIcon,
                modifier = Modifier
                    .align(iconAlignment)
                    .padding(iconPadding)
            )
        }
    }
}