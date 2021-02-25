package ober.gondolin.common.viewmodel.start

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ober.gondolin.common.navigation.Screen
import ober.gondolin.common.utils.UtilsModule
import ober.gondolin.common.utils.encryption.RandomGenerator
import ober.gondolin.common.utils.simpleStorage.SimpleStorage
import ober.gondolin.common.viewmodel.BaseViewModel
import org.kodein.di.instance

class NewUserViewModel(
    viewModelScope: CoroutineScope
): BaseViewModel() {

    private val simpleStorage: SimpleStorage by UtilsModule.di.instance()

    val encryptionKey = MutableStateFlow("")
    val pin = MutableStateFlow("")

    private val _doneButtonEnabled = MutableStateFlow(false).apply {
        viewModelScope.launch {
            encryptionKey.combine(pin) { key, pin ->
                key.isNotBlank() && pin.isNotBlank()
            }.collect {
                value = it
            }
        }
    }
    val doneButtonEnabled: StateFlow<Boolean> = _doneButtonEnabled

    fun onGenerateClicked() {
        encryptionKey.value = RandomGenerator.generateRandomKey(DEFAULT_KEY_LENGTH)
    }

    fun onDoneClicked() {
        simpleStorage.saveEncryptionKey(encryptionKey = encryptionKey.value, pin = pin.value)
        navigator.navigate(Screen.NewUser.ToCategoriesScreen)
    }

    companion object {
        const val DEFAULT_KEY_LENGTH = 24
    }
}