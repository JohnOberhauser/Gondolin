package ober.gondolin.common.ui.screens.start

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ober.gondolin.common.viewmodel.start.SplashScreenViewModel

class SplashScreen {
    private lateinit var viewModel: SplashScreenViewModel

    @Composable
    fun Create() {
        viewModel = SplashScreenViewModel()

        Content()
        viewModel.start()
    }

    @Composable
    private fun Content() {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.primary
        ) {
        }
    }
}