package ober.gondolin.common.viewmodel

import ober.gondolin.common.navigation.Navigator
import ober.gondolin.common.viewmodel.start.SplashScreenViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

object NavigationModule {
    private val module = DI.Module("NavigationModule") {
        bind<Navigator<TopLevelScreen>>() with singleton {
            Navigator(SplashScreenViewModel())
        }
        bind<Navigator<MainLevelScreen>>() with singleton {
            Navigator(MainLevelScreen.Categories())
        }
    }

    val di = DI {
        import(module)
    }
}