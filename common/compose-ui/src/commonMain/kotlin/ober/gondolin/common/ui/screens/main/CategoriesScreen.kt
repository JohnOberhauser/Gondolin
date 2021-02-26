package ober.gondolin.common.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class CategoriesScreen {

    @Composable
    fun Create() {
        Content()
    }

    @Composable
    private fun Content() {
        Text(
            text = "Test"
        )
        Box(
            modifier = Modifier
                .height(5.dp)
                .background(
                    color = MaterialTheme.colors.error
                )
        ) {

        }

        Surface {  }
    }
}