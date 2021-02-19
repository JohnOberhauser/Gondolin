package ober.gondolin.desktop

import androidx.compose.desktop.DesktopTheme
import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.rootComponent
import com.arkivanov.decompose.lifecycle.LifecycleRegistry
import com.arkivanov.decompose.lifecycle.resume
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.badoo.reaktive.coroutinesinterop.asScheduler
import com.badoo.reaktive.scheduler.overrideSchedulers
import ober.gondolin.common.database.TodoDatabaseDriver
import ober.gondolin.common.root.TodoRoot
import ober.gondolin.common.ui.TodoRootContent
import ober.gondolin.database.TodoDatabase
import kotlinx.coroutines.Dispatchers

fun main() {
    overrideSchedulers(main = Dispatchers.Main::asScheduler)

    val lifecycle = LifecycleRegistry()
    lifecycle.resume()

    Window("Gondolin") {
        Surface(modifier = Modifier.fillMaxSize()) {
            MaterialTheme {
                DesktopTheme {
                    TodoRootContent(rootComponent(factory = ::todoRoot))
                }
            }
        }
    }
}

private fun todoRoot(componentContext: ComponentContext): TodoRoot =
    TodoRoot(
        componentContext = componentContext,
        dependencies = object : TodoRoot.Dependencies {
            override val storeFactory = DefaultStoreFactory
            override val database = TodoDatabase(TodoDatabaseDriver())
        }
    )
