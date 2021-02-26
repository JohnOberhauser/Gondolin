package ober.gondolin.common.viewmodel.start

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ober.gondolin.common.navigation.NavigationModule
import ober.gondolin.common.navigation.Navigator
import ober.gondolin.common.navigation.TopLevelScreen
import ober.gondolin.common.utils.UtilsModule
import ober.gondolin.common.utils.encryption.KeyManager
import ober.gondolin.common.utils.encryption.RandomGenerator
import ober.gondolin.common.utils.simpleStorage.SimpleStorage
import ober.gondolin.common.viewmodel.BaseViewModel
import org.kodein.di.instance

class NewUserViewModel(
    viewModelScope: CoroutineScope
): BaseViewModel() {

    private val navigator: Navigator<TopLevelScreen> by NavigationModule.di.instance()
    private val simpleStorage: SimpleStorage by UtilsModule.di.instance()

    val encryptionKey = MutableStateFlow("")
    val reEnteredEncryptionKey = MutableStateFlow("")
    val pin = MutableStateFlow("")

    private val _doneButtonEnabled = MutableStateFlow(false).apply {
        viewModelScope.launch {
            combine(encryptionKey, reEnteredEncryptionKey, pin) { key, key2, pin ->
                key.isNotBlank() && pin.isNotBlank() && key == key2
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
        simpleStorage.saveString(
            string = encryptionKey.value,
            encryptionKey = pin.value,
            key = SimpleStorage.Key.ENCRYPTION_KEY
        )
        KeyManager.key = encryptionKey.value
        navigator.navigate(TopLevelScreen.NewUser.ToCategoriesScreen)
    }

    companion object {
        const val DEFAULT_KEY_LENGTH = 24
    }
}