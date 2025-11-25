package com.woon.modernandroidvideostreamingarchitecture.detail.sceen.success.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.theme.CustomTheme

@Composable
fun TagsSection(
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    if (tags.isEmpty()) return

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Tags",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.onBackground
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tags.size) { index ->
                Text(
                    text = tags[index],
                    style = MaterialTheme.typography.bodySmall,
                    color = CustomTheme.colors.onSurfaceVariant
                )
            }
        }
    }
}