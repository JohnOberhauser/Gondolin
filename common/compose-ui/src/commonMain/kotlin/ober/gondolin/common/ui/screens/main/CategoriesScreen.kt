package ober.gondolin.common.ui.screens.main

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class CategoriesScreen() {

    @Composable
    fun Create() {
        Content()
    }

    @Composable
    private fun Content() {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = MaterialTheme.colors.error
        ) {

        }
    }
}