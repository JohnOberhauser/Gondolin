package ober.gondolin.common.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ober.gondolin.common.navigation.MainLevelScreen
import ober.gondolin.common.navigation.NavigationModule
import ober.gondolin.common.navigation.Navigator
import ober.gondolin.common.navigation.TopLevelScreen
import ober.gondolin.common.ui.screens.start.NewUserScreen
import ober.gondolin.common.ui.screens.start.SplashScreen
import ober.gondolin.common.ui.screens.start.UnlockScreen
import org.kodein.di.instance

class MainRoot {

    private val navigator: Navigator<MainLevelScreen> by NavigationModule.di.instance()

    @Composable
    fun Create() {
        Content()
    }

    @Composable
    fun Content() {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.error
        ) {
            val currentScreen = navigator.currentScreen.collectAsState()

            when (currentScreen.value) {
                is MainLevelScreen.CategoriesScreen -> CategoriesScreen().Create()
            }
        }
    }
}