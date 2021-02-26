package ober.gondolin.common.viewmodel.start

import ober.gondolin.common.navigation.NavigationModule
import ober.gondolin.common.navigation.Navigator
import ober.gondolin.common.navigation.TopLevelScreen
import ober.gondolin.common.utils.UtilsModule
import ober.gondolin.common.utils.simpleStorage.SimpleStorage
import ober.gondolin.common.viewmodel.BaseViewModel
import org.kodein.di.instance

class SplashScreenViewModel: BaseViewModel() {

    private val navigator: Navigator<TopLevelScreen> by NavigationModule.di.instance()
    private val simpleStorage: SimpleStorage by UtilsModule.di.instance()

    fun start() {
        if (simpleStorage.doesValueExist(SimpleStorage.Key.ENCRYPTION_KEY)) {
            navigator.navigate(TopLevelScreen.Splash.ToUnlockScreen)
        } else {
            navigator.navigate(TopLevelScreen.Splash.ToNewUserScreen)
        }
    }
}