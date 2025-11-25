package com.woon.modernandroidvideostreamingarchitecture.main.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.woon.modernandroidvideostreamingarchitecture.core.localprovider.LocalNavController
import com.woon.modernandroidvideostreamingarchitecture.favorite.FavoriteScreen
import com.woon.modernandroidvideostreamingarchitecture.home.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = modifier
        ) {
            composable(
                route = "home"
            ) {
                HomeScreen()
            }

            composable(
                route = "favorite",
            ) {
                FavoriteScreen()
            }

            composable(
                route = "detail/{id}/{type}",
                arguments = listOf(
                    navArgument("id") { type = NavType.LongType },
                    navArgument("type") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getLong("id") ?: 0L
                val type = backStackEntry.arguments?.getString("type") ?: ""
//                DetailScreen(
//                    id = id,
//                    type = type
//                )
            }
        }
    }
}
