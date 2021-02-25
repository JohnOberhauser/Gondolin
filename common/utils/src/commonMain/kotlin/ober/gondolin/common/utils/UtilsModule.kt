package ober.gondolin.common.utils

import ober.gondolin.common.utils.simpleStorage.RushSimpleStorage
import ober.gondolin.common.utils.simpleStorage.SimpleStorage
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

object UtilsModule {
    private val module = DI.Module("UtilsModule") {
        bind<SimpleStorage>() with singleton { RushSimpleStorage() }
    }

    val di = DI {
        import(module)
    }
}