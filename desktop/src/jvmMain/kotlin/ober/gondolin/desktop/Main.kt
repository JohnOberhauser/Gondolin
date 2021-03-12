package ober.gondolin.desktop

import androidx.compose.desktop.DesktopTheme
import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.lifecycle.LifecycleRegistry
import com.arkivanov.decompose.lifecycle.resume
import com.badoo.reaktive.coroutinesinterop.asScheduler
import com.badoo.reaktive.scheduler.overrideSchedulers
import kotlinx.coroutines.Dispatchers
import ober.gondolin.common.ui.screens.Root
import ober.gondolin.common.ui.screens.start.NewUserScreen
import ober.gondolin.common.ui.theme.ComposeAppTheme

fun main() {
    overrideSchedulers(main = Dispatchers.Main::asScheduler)

    val lifecycle = LifecycleRegistry()
    lifecycle.resume()

    Window(
        "Gondolin",
        resizable = false
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            ComposeAppTheme {
                DesktopTheme {
                    Root().Create()
                }
            }
        }
    }
}
