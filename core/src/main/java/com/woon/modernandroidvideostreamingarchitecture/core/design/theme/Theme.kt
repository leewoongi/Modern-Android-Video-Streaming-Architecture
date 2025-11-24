package com.woon.modernandroidvideostreamingarchitecture.core.design.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.woon.modernandroidvideostreamingarchitecture.core.design.theme.theme.CustomTheme

@Composable
fun ModernAndroidVideoStreamingArchitectureTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme(
            primary = CustomTheme.colors.primary,
            onPrimary = CustomTheme.colors.onPrimary,
            primaryContainer = CustomTheme.colors.primaryContainer,
            onPrimaryContainer = CustomTheme.colors.onPrimaryContainer,
            inversePrimary = CustomTheme.colors.inversePrimary,
            secondary = CustomTheme.colors.secondary,
            onSecondary = CustomTheme.colors.onSecondary,
            secondaryContainer = CustomTheme.colors.secondaryContainer,
            onSecondaryContainer = CustomTheme.colors.onSecondaryContainer,
            tertiary = CustomTheme.colors.tertiary,
            onTertiary = CustomTheme.colors.onTertiary,
            background = CustomTheme.colors.background,
            onBackground = CustomTheme.colors.onBackground,
            surface = CustomTheme.colors.surface,
            onSurface = CustomTheme.colors.onSurface,
            surfaceVariant = CustomTheme.colors.surfaceVariant,
            onSurfaceVariant = CustomTheme.colors.onSurfaceVariant,
            inverseSurface = CustomTheme.colors.inverseSurface,
            inverseOnSurface = CustomTheme.colors.inverseOnSurface,
            error = CustomTheme.colors.error,
            onError = CustomTheme.colors.onError,
            outline = CustomTheme.colors.outline,
            outlineVariant = CustomTheme.colors.outlineVariant,
            scrim = CustomTheme.colors.scrim
        )
        else -> lightColorScheme(
            primary = CustomTheme.colors.primary,
            onPrimary = CustomTheme.colors.onPrimary,
            primaryContainer = CustomTheme.colors.primaryContainer,
            onPrimaryContainer = CustomTheme.colors.onPrimaryContainer,
            inversePrimary = CustomTheme.colors.inversePrimary,
            secondary = CustomTheme.colors.secondary,
            onSecondary = CustomTheme.colors.onSecondary,
            secondaryContainer = CustomTheme.colors.secondaryContainer,
            onSecondaryContainer = CustomTheme.colors.onSecondaryContainer,
            tertiary = CustomTheme.colors.tertiary,
            onTertiary = CustomTheme.colors.onTertiary,
            background = CustomTheme.colors.background,
            onBackground = CustomTheme.colors.onBackground,
            surface = CustomTheme.colors.surface,
            onSurface = CustomTheme.colors.onSurface,
            surfaceVariant = CustomTheme.colors.surfaceVariant,
            onSurfaceVariant = CustomTheme.colors.onSurfaceVariant,
            inverseSurface = CustomTheme.colors.inverseSurface,
            inverseOnSurface = CustomTheme.colors.inverseOnSurface,
            error = CustomTheme.colors.error,
            onError = CustomTheme.colors.onError,
            outline = CustomTheme.colors.outline,
            outlineVariant = CustomTheme.colors.outlineVariant,
            scrim = CustomTheme.colors.scrim
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}