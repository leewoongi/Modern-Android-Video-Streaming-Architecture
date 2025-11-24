package com.woon.modernandroidvideostreamingarchitecture.home.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.MvsSearchBar
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.MvsText
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.color.colorScrim

@Composable
fun HomeSearchScreen(
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

    MvsText(
        text = query,
        color = colorScrim,
        typography = MaterialTheme.typography.titleLarge
    )
}