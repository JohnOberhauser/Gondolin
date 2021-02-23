package ober.gondolin.common.viewmodel.start

import kotlinx.coroutines.flow.MutableStateFlow
import ober.gondolin.common.utils.encryption.RandomGenerator
import ober.gondolin.common.viewmodel.BaseViewModel

class NewUserViewModel: BaseViewModel() {

    val encryptionKey = MutableStateFlow("")

    fun onGenerateClicked() {
        encryptionKey.value = RandomGenerator.generateRandomKey(DEFAULT_KEY_LENGTH)
    }

    companion object {
        const val DEFAULT_KEY_LENGTH = 24
    }
}