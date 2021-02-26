package ober.gondolin.common.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class Screen {

    val screenScope: CoroutineScope = CoroutineScope(SupervisorJob())

    protected fun onCleared() {
        screenScope.coroutineContext.cancel()
    }

    fun clear() {
        onCleared()
    }
}