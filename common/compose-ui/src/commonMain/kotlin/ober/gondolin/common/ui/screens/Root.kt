package ober.gondolin.common.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ober.gondolin.common.navigation.Navigator
import ober.gondolin.common.navigation.Screen
import ober.gondolin.common.ui.screens.main.CategoriesScreen
import ober.gondolin.common.ui.screens.start.NewUserScreen
import ober.gondolin.common.ui.screens.start.SplashScreen

@Composable
fun Root() {
    val navigator = Navigator(Screen.Splash)

    val currentScreen = remember {
        mutableStateOf(navigator.currentScreen.value)
    }

    when (currentScreen.value) {
        is Screen.Splash -> SplashScreen().Create()
        is Screen.NewUser -> NewUserScreen().Create()
        is Screen.Categories -> CategoriesScreen().Create()
    }

    rememberCoroutineScope().launch {
        navigator.currentScreen.collect {
            if (it is Screen.Splash || it == currentScreen.value) {
                return@collect
            }
            currentScreen.value = it
        }
    }
}