package com.woon.modernandroidvideostreamingarchitecture.core.design.theme.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.color.CustomColor
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.color.LocalColors

object CustomTheme {
    val colors: CustomColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}