package com.woon.modernandroidvideostreamingarchitecture.main.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Home : BottomNavItem(
        route = "home",
        title = "홈",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    object Favorites : BottomNavItem(
        route = "favorite",
        title = "즐겨찾기",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Filled.FavoriteBorder
    )

    companion object {
        // 여기에 상수로 정의!
        val items = listOf(Home, Favorites)
    }
}