package com.woon.modernandroidvideostreamingarchitecture.core.design.component.paging

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T: Any> PagingRefreshStateItem(
    items: LazyPagingItems<T>,
    loadingContent: @Composable () -> Unit,
    errorContent: @Composable (Throwable) -> Unit,
    emptyContent: @Composable () -> Unit,
    successContent: @Composable (LazyPagingItems<T>) -> Unit,
){
    val loadState = items.loadState.refresh

    when {
        loadState is LoadState.Loading -> { loadingContent() }
        loadState is LoadState.Error -> { errorContent(loadState.error) }
        loadState is LoadState.NotLoading && items.itemCount == 0 -> { emptyContent() }
        else -> { successContent(items) }
    }
}