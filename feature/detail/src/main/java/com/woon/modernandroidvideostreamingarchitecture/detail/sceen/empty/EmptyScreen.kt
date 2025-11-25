package com.woon.modernandroidvideostreamingarchitecture.detail.sceen.empty

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.datacase.EmptyDataCase

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    message: String,
){
    EmptyDataCase(
        modifier = modifier,
        message = message
    )
}