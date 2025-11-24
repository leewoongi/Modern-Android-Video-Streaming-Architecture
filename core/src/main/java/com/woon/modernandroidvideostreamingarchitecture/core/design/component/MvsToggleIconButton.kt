package com.woon.modernandroidvideostreamingarchitecture.core.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.theme.CustomTheme

@Composable
fun MvsToggleIconButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onSelectionChange: () -> Unit,
    showCheckIcon: Boolean = false,
    selectedIcon: ImageVector = Icons.Default.Favorite,
    unselectedIcon: ImageVector = Icons.Default.FavoriteBorder,
    checkIcon: ImageVector = Icons.Default.Check,
    selectedTint: Color = CustomTheme.colors.error,
    unselectedTint: Color = CustomTheme.colors.onSurface.copy(alpha = 0.6f),
    checkTint: Color = CustomTheme.colors.primary,
    iconSize: Dp = 18.dp,
    buttonSize: Dp = 32.dp,
    contentPadding: Dp = 4.dp,
    backgroundColor: Color = CustomTheme.colors.surface.copy(alpha = 0.95f),
) {
    Box(
        modifier = modifier
            .size(buttonSize)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = { onSelectionChange() },
            modifier = Modifier.padding(contentPadding)
        ) {
            Icon(
                imageVector = when {
                    showCheckIcon -> checkIcon
                    isSelected -> selectedIcon
                    else -> unselectedIcon
                },
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = when {
                    showCheckIcon -> checkTint
                    isSelected -> selectedTint
                    else -> unselectedTint
                }
            )
        }
    }
}
