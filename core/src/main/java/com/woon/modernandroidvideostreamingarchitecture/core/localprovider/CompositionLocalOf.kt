package com.woon.modernandroidvideostreamingarchitecture.core.localprovider

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController> {
    error("NavController not provided")
}

val LocalSnackBarHostState = compositionLocalOf<SnackbarHostState> {
    error("No SnackbarHostState provided")
}