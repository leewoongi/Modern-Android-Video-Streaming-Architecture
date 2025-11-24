package com.woon.modernandroidvideostreamingarchitecture.core.design.component.paging

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingAppendStateFooter(
    items: LazyPagingItems<T>,
    loadingContent: @Composable () -> Unit,
    errorContent: @Composable (Throwable) -> Unit,
) {
    val loadState = items.loadState.append
    when (loadState) {
        is LoadState.Loading -> loadingContent()
        is LoadState.Error -> errorContent(loadState.error)
        is LoadState.NotLoading -> Unit
    }
}