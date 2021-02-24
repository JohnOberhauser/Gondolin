package ober.gondolin.common.viewmodel

import kotlinx.coroutines.CoroutineScope

abstract class BaseViewModel(
    val viewModelScope: CoroutineScope
)