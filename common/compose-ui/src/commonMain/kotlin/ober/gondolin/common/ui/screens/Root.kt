package ober.gondolin.common.ui.screens

import androidx.compose.runtime.*
import ober.gondolin.common.navigation.NavigationModule
import ober.gondolin.common.navigation.Navigator
import ober.gondolin.common.navigation.Screen
import ober.gondolin.common.ui.screens.main.CategoriesScreen
import ober.gondolin.common.ui.screens.start.NewUserScreen
import ober.gondolin.common.ui.screens.start.SplashScreen
import ober.gondolin.common.ui.screens.start.UnlockScreen
import org.kodein.di.instance

class Root {

    private val navigator: Navigator by NavigationModule.di.instance()

    @Composable
    fun Create() {
        val currentScreen = navigator.currentScreen.collectAsState()

        when (currentScreen.value) {
            is Screen.Splash -> SplashScreen().Create()
            is Screen.NewUser -> NewUserScreen().Create()
            is Screen.Categories -> CategoriesScreen().Create()
            is Screen.Unlock -> UnlockScreen().Create()
        }
    }
}