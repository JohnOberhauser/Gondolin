package ober.gondolin.common.viewmodel.start

import cc.popkorn.inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ober.gondolin.common.utils.encryption.RandomGenerator
import ober.gondolin.common.utils.simpleStorage.SimpleStorage
import ober.gondolin.common.viewmodel.BaseViewModel

class NewUserViewModel(
    viewModelScope: CoroutineScope
): BaseViewModel(
    viewModelScope
) {

    private val simpleStorage: SimpleStorage = inject()

    val encryptionKey = MutableStateFlow("")
    val pin = MutableStateFlow("").apply {
        viewModelScope.launch {
            collect {
                doneButtonEnabled.value = it.isNotBlank()
            }
        }
    }

    val doneButtonEnabled = MutableStateFlow(false)

    fun onGenerateClicked() {
        encryptionKey.value = RandomGenerator.generateRandomKey(DEFAULT_KEY_LENGTH)
    }

    fun onDoneClicked() {
        simpleStorage.saveEncryptionKey(encryptionKey = encryptionKey.value, pin = pin.value)
    }

    companion object {
        const val DEFAULT_KEY_LENGTH = 24
    }
}