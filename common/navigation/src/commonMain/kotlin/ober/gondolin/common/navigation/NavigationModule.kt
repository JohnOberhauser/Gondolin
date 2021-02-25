package ober.gondolin.common.navigation

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

object NavigationModule {
    private val module = DI.Module("NavigationModule") {
        bind<Navigator>() with singleton { Navigator(Screen.Splash) }
    }

    val di = DI {
        import(module)
    }
}