package ober.gondolin.common.navigation

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

object NavigationModule {
    private val module = DI.Module("NavigationModule") {
        bind<Navigator<TopLevelScreen>>() with singleton {
            Navigator(TopLevelScreen.Splash)
        }
        bind<Navigator<MainLevelScreen>>() with singleton {
            Navigator(MainLevelScreen.CategoriesScreen)
        }
    }

    val di = DI {
        import(module)
    }
}