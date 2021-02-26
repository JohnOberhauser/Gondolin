package ober.gondolin.common.viewmodel.start

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ober.gondolin.common.navigation.Navigator
import ober.gondolin.common.utils.UtilsModule
import ober.gondolin.common.utils.encryption.KeyManager
import ober.gondolin.common.utils.simpleStorage.SimpleStorage
import ober.gondolin.common.viewmodel.NavigationModule
import ober.gondolin.common.viewmodel.TopLevelScreen
import org.kodein.di.instance

class UnlockViewModel: TopLevelScreen.Unlock() {

    private val navigator: Navigator<TopLevelScreen> by NavigationModule.di.instance()
    private val simpleStorage: SimpleStorage by UtilsModule.di.instance()

    val pin = MutableStateFlow("")

    private val _unlockButtonEnabled = MutableStateFlow(false).apply {
        screenScope.launch {
            pin.collect {
                value = it.isNotBlank()
            }
        }
    }
    val unlockButtonEnabled: StateFlow<Boolean> = _unlockButtonEnabled

    fun onUnlockClicked() {
        val key = simpleStorage.getString(pin.value, SimpleStorage.Key.ENCRYPTION_KEY)
        if (key.isBlank()) {
            pin.value = ""
        } else {
            KeyManager.key = key
            navigator.navigate(ToCategoriesScreen)
        }
    }
}