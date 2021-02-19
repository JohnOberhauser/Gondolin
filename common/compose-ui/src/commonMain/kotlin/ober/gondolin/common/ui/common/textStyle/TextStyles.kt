package ober.gondolin.common.ui.common.textStyle

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

object TextStyles {

    fun commonTextStyle(): TextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold
    )

}