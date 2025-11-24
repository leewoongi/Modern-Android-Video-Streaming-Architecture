package com.woon.modernandroidvideostreamingarchitecture.core.design.component.datacase

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.woon.modernandroidvideostreamingarchitecture.core.design.component.MvsText

@Composable
fun ErrorDataCase(
    modifier: Modifier = Modifier,
    error: Throwable,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        MvsText(
            modifier = Modifier.clickable(onClick = onClick),
            text = "${error.message}",
            color = MaterialTheme.colorScheme.error,
            typography = MaterialTheme.typography.bodyMedium
        )
    }
}
