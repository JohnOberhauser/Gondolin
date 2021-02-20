package ober.gondolin.common.ui.theme

import androidx.compose.runtime.Composable

@Composable
fun ComposeAppTheme(darkTheme: Boolean? = null, content: @Composable () -> Unit) {
    val shouldUseDarkTheme = darkTheme ?: isSystemInDarkTheme()

    val colors = if (shouldUseDarkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    AppMaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

