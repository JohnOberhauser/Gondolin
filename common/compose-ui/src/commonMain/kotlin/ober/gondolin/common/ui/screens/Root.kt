package ober.gondolin.common.ui.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import ober.gondolin.common.navigation.NavigationModule
import ober.gondolin.common.navigation.Navigator
import ober.gondolin.common.navigation.TopLevelScreen
import ober.gondolin.common.ui.screens.main.MainRoot
import ober.gondolin.common.ui.screens.start.NewUserScreen
import ober.gondolin.common.ui.screens.start.SplashScreen
import ober.gondolin.common.ui.screens.start.UnlockScreen
import org.kodein.di.instance

class Root {

    private val navigator: Navigator<TopLevelScreen> by NavigationModule.di.instance()

    @Composable
    fun Create() {
        Surface(color = MaterialTheme.colors.background) {
            val currentScreen = navigator.currentScreen.collectAsState()

            when (currentScreen.value) {
                is TopLevelScreen.Splash -> SplashScreen().Create()
                is TopLevelScreen.NewUser -> NewUserScreen().Create()
                is TopLevelScreen.Unlock -> UnlockScreen().Create()
                is TopLevelScreen.Main -> MainRoot().Create()
            }
        }
    }
}