package com.woon.modernandroidvideostreamingarchitecture.favorite.screen.error

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.datacase.ErrorDataCase

@Composable
fun ErrorScreen(
    modifier: Modifier,
    error: Throwable,
    onClick: () -> Unit = {}
){
    ErrorDataCase(
        modifier = modifier,
        error = error,
        onClick = onClick
    )
}