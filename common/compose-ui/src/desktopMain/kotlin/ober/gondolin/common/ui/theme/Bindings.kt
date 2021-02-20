package ober.gondolin.common.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable

@Composable
actual fun isSystemInDarkTheme(): Boolean = false

@Composable
actual fun AppMaterialTheme(colors: Colors, typography: Typography, shapes: Shapes, content: @Composable () -> Unit) {
    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}