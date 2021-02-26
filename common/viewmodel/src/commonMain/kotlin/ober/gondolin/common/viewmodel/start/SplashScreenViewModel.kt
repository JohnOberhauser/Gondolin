package ober.gondolin.common.viewmodel.start

import kotlinx.coroutines.launch
import ober.gondolin.common.navigation.Navigator
import ober.gondolin.common.utils.UtilsModule
import ober.gondolin.common.utils.simpleStorage.SimpleStorage
import ober.gondolin.common.viewmodel.NavigationModule
import ober.gondolin.common.viewmodel.TopLevelScreen
import org.kodein.di.instance

class SplashScreenViewModel: TopLevelScreen.Splash() {

    private val navigator: Navigator<TopLevelScreen> by NavigationModule.di.instance()
    private val simpleStorage: SimpleStorage by UtilsModule.di.instance()

    init {
        start()
    }

    private fun start() {
        screenScope.launch {
            if (simpleStorage.doesValueExist(SimpleStorage.Key.ENCRYPTION_KEY)) {
                navigator.navigate(ToUnlockScreen)
            } else {
                navigator.navigate(ToNewUserScreen)
            }
        }
    }
}