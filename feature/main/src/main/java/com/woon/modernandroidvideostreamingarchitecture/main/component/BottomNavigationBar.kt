package com.woon.modernandroidvideostreamingarchitecture.main.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.theme.CustomTheme
import com.woon.modernandroidvideostreamingarchitecture.main.model.BottomNavItem
import com.woon.modernandroidvideostreamingarchitecture.main.model.BottomNavItem.Companion.items

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // detail 화면에서는 bottom bar 숨기기
    if (currentRoute?.startsWith("detail") == true) {
        return
    }

    NavigationBar(
        modifier = modifier,
        containerColor = CustomTheme.colors.background
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationRoute ?: "") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) {
                            item.selectedIcon
                        } else {
                            item.unselectedIcon
                        },
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) }
            )
        }
    }
}
