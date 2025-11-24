package com.woon.modernandroidvideostreamingarchitecture.core.design.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woon.modernandroidvideostreamingarchitecture.core.R
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.color.colorOnSurface
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.color.colorOnSurfaceVariant
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.color.colorOutlineVariant
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.color.colorPrimary
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.color.colorSurface

@Composable
fun MvsSearchBar(
    modifier: Modifier = Modifier,
    query: String = "",
    onSearch: (String) -> Unit = {}
){
    var searchText by rememberSaveable { mutableStateOf(query) }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = searchText,
        onValueChange = { searchText = it },
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = colorOnSurface
        ),
        placeholder = {
            MvsText(
                text = stringResource(R.string.search_placeholder),
                color = colorOnSurfaceVariant,
                typography = TextStyle(
                    fontSize = 16.sp
                )
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_icon_description),
                tint = colorOnSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.search_clear_description),
                    tint = colorOnSurface,
                    modifier = Modifier
                        .size(14.dp)
                        .clickable {
                            searchText = ""
                        },
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = colorSurface,
            unfocusedContainerColor = colorSurface,
            focusedTextColor = colorOnSurface,
            unfocusedTextColor = colorOnSurface,
            cursorColor = colorPrimary,
            focusedBorderColor = colorOutlineVariant,
            unfocusedBorderColor = colorOutlineVariant
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(searchText)
                keyboardController?.hide()
            }
        ),
        modifier = modifier
    )
}