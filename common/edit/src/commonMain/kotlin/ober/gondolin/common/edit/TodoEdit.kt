package ober.gondolin.common.edit

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import ober.gondolin.common.edit.TodoEdit.Dependencies
import ober.gondolin.common.edit.integration.TodoEditImpl
import ober.gondolin.database.TodoDatabase

interface TodoEdit {

    val models: Value<Model>

    fun onTextChanged(text: String)

    fun onDoneChanged(isDone: Boolean)

    fun onCloseClicked()

    data class Model(
        val text: String,
        val isDone: Boolean
    )

    interface Dependencies {
        val storeFactory: StoreFactory
        val database: TodoDatabase
        val itemId: Long
        val editOutput: Consumer<Output>
    }

    sealed class Output {
        object Finished : Output()
    }
}

@Suppress("FunctionName") // Factory function
fun TodoEdit(componentContext: ComponentContext, dependencies: Dependencies): TodoEdit =
    TodoEditImpl(componentContext, dependencies)
