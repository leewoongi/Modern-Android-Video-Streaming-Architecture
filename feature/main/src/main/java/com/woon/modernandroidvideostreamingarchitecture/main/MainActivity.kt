package com.woon.modernandroidvideostreamingarchitecture.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.woon.modernandroidvideostreamingarchitecture.core.localprovider.LocalSnackBarHostState
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.ModernAndroidVideoStreamingArchitectureTheme
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.theme.CustomTheme
import com.woon.modernandroidvideostreamingarchitecture.main.component.BottomNavigationBar
import com.woon.modernandroidvideostreamingarchitecture.main.component.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val snackBarHostState = remember { SnackbarHostState() }

            ModernAndroidVideoStreamingArchitectureTheme {
                val snackBarHostState = remember { SnackbarHostState() }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = CustomTheme.colors.background,
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController
                        )
                    },
                    snackbarHost = { snackBarHostState }
                ) { innerPadding ->
                    CompositionLocalProvider(
                        LocalSnackBarHostState provides snackBarHostState
                    ) {
                        NavigationGraph(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
