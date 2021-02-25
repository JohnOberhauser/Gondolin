package ober.gondolin.common.viewmodel.start

import ober.gondolin.common.navigation.Screen
import ober.gondolin.common.utils.UtilsModule
import ober.gondolin.common.utils.simpleStorage.SimpleStorage
import ober.gondolin.common.viewmodel.BaseViewModel
import org.kodein.di.instance

class SplashScreenViewModel: BaseViewModel() {

    private val simpleStorage: SimpleStorage by UtilsModule.di.instance()

    fun start() {
        if (simpleStorage.hasSavedEncryptionKey) {
            navigator.navigate(Screen.Splash.ToUnlockScreen)
        } else {
            navigator.navigate(Screen.Splash.ToNewUserScreen)
        }
    }
}