package ober.gondolin.common.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import ober.gondolin.common.edit.TodoEdit
import ober.gondolin.common.main.TodoMain
import ober.gondolin.common.root.TodoRoot.Dependencies
import ober.gondolin.common.root.integration.TodoRootImpl
import ober.gondolin.database.TodoDatabase

interface TodoRoot {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        data class Main(val component: TodoMain) : Child()
        data class Edit(val component: TodoEdit) : Child()
    }

    interface Dependencies {
        val storeFactory: StoreFactory
        val database: TodoDatabase
    }
}

@Suppress("FunctionName") // Factory function
fun TodoRoot(componentContext: ComponentContext, dependencies: Dependencies): TodoRoot =
    TodoRootImpl(
        componentContext = componentContext,
        todoMain = { childContext, output ->
            TodoMain(
                componentContext = childContext,
                dependencies = object : TodoMain.Dependencies, Dependencies by dependencies {
                    override val mainOutput: Consumer<TodoMain.Output> = output
                }
            )
        },
        todoEdit = { childContext, itemId, output ->
            TodoEdit(
                componentContext = childContext,
                dependencies = object : TodoEdit.Dependencies, Dependencies by dependencies {
                    override val itemId: Long = itemId
                    override val editOutput: Consumer<TodoEdit.Output> = output
                }
            )
        }
    )
