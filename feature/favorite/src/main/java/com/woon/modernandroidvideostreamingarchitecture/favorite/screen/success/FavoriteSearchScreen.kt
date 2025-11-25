package com.woon.modernandroidvideostreamingarchitecture.favorite.screen.success

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.MvsSearchBar

@Composable
fun FavoriteSearchScreen(
    modifier: Modifier = Modifier,
    query: String,
    onSearch: (String) -> Unit = {}
){
    MvsSearchBar(
        modifier = modifier,
        onSearch = {
            onSearch(it)
        }
    )

    Spacer(modifier = Modifier.height(8.dp))
}