package ober.gondolin.common.ui.screens.start

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ober.gondolin.common.navigation.Navigator
import ober.gondolin.common.navigation.Screen
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
                .fillMaxWidth()
                .fillMaxHeight(),
            color = MaterialTheme.colors.primary
        ) {
        }
    }
}