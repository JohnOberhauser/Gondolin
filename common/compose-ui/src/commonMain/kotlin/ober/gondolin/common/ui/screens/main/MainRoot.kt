package ober.gondolin.common.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ober.gondolin.common.navigation.Navigator
import ober.gondolin.common.viewmodel.MainLevelScreen
import ober.gondolin.common.viewmodel.NavigationModule
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
                is MainLevelScreen.Categories -> CategoriesScreen().Create()
            }
        }
    }
}