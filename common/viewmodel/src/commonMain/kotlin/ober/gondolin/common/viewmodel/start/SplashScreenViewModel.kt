package ober.gondolin.common.viewmodel.start

import ober.gondolin.common.navigation.Screen
import ober.gondolin.common.viewmodel.BaseViewModel

class SplashScreenViewModel: BaseViewModel() {
    fun start() {
        navigator.navigate(Screen.Splash.ToNewUserScreen)
    }
}