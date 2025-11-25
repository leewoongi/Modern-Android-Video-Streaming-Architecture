package com.woon.modernandroidvideostreamingarchitecture.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.datacase.EmptyDataCase
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.datacase.ErrorDataCase
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.datacase.LoadingDataCase
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.paging.PagingRefreshStateItem
import com.woon.modernandroidvideostreamingarchitecture.home.intent.HomeIntent
import com.woon.modernandroidvideostreamingarchitecture.home.screen.HomeMediaListScreen
import com.woon.modernandroidvideostreamingarchitecture.home.screen.HomeSearchScreen

@Composable
fun HomeScreen(

) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val gridState = rememberLazyGridState()

    val query by viewModel.query.collectAsStateWithLifecycle()
    val media = viewModel.media.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        HomeSearchScreen(
            modifier = Modifier
                .fillMaxWidth(),
            query = query,
            onSearch = {
                viewModel.processIntent(HomeIntent.Search(it))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        PagingRefreshStateItem(
            items = media,
            loadingContent = { LoadingDataCase() },
            errorContent = { error -> ErrorDataCase(error = error) },
            emptyContent = { EmptyDataCase(message = "데이터가 없습니다.") },
            successContent = { items ->
                HomeMediaListScreen(
                    media = items,
                    state = gridState,
                    onClickItem = { /**디테일 화면 이동*/ },
                    onFavoriteClick = { viewModel.processIntent(HomeIntent.OnClickFavorite(it)) }
                )
            }
        )
    }
}