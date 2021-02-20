package ober.gondolin.common.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable

@Composable
expect fun isSystemInDarkTheme(): Boolean

@Composable
expect fun AppMaterialTheme(colors: Colors, typography: Typography, shapes: Shapes, content: @Composable () -> Unit)