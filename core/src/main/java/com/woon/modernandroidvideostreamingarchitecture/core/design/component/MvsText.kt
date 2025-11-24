package com.woon.modernandroidvideostreamingarchitecture.core.design.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MvsText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    typography: TextStyle,
    textAlign: TextAlign = TextAlign.Start,
    maxLine: Int = Int.MAX_VALUE,
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        style = typography,
        textAlign = textAlign,
        maxLines = maxLine
    )
}