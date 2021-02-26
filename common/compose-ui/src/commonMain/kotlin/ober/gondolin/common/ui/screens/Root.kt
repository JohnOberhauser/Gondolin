package ober.gondolin.common.ui.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import ober.gondolin.common.navigation.Navigator
import ober.gondolin.common.ui.screens.main.MainRoot
import ober.gondolin.common.ui.screens.start.NewUserScreen
import ober.gondolin.common.ui.screens.start.UnlockScreen
import ober.gondolin.common.viewmodel.NavigationModule
import ober.gondolin.common.viewmodel.TopLevelScreen
import ober.gondolin.common.viewmodel.start.NewUserViewModel
import ober.gondolin.common.viewmodel.start.UnlockViewModel
import org.kodein.di.instance

class Root {

    private val navigator: Navigator<TopLevelScreen> by NavigationModule.di.instance()

    @Composable
    fun Create() {
        Surface(color = MaterialTheme.colors.background) {
            val currentScreen = navigator.currentScreen.collectAsState()

            currentScreen.value.let { screen ->
                when (screen) {
                    is TopLevelScreen.Splash -> {}
                    is TopLevelScreen.NewUser -> NewUserScreen().Create(screen as NewUserViewModel)
                    is TopLevelScreen.Unlock -> UnlockScreen().Create(screen as UnlockViewModel)
                    is TopLevelScreen.Main -> MainRoot().Create()
                }
            }
        }
    }
}