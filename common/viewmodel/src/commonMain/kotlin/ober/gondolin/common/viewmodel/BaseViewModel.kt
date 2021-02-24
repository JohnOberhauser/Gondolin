package ober.gondolin.common.viewmodel

import kotlinx.coroutines.CoroutineScope
import ober.gondolin.common.utils.UtilsModule
import org.kodein.di.DI

abstract class BaseViewModel(
    val viewModelScope: CoroutineScope
) {
    val di = DI {
        import(UtilsModule.module)
    }
}