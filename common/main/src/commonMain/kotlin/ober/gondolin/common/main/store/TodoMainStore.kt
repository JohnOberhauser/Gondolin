package ober.gondolin.common.main.store

import com.arkivanov.mvikotlin.core.store.Store
import ober.gondolin.common.main.TodoItem
import ober.gondolin.common.main.store.TodoMainStore.Intent
import ober.gondolin.common.main.store.TodoMainStore.State

internal interface TodoMainStore : Store<Intent, State, Nothing> {

    sealed class Intent {
        data class SetItemDone(val id: Long, val isDone: Boolean) : Intent()
        data class DeleteItem(val id: Long) : Intent()
        data class SetText(val text: String) : Intent()
        object AddItem : Intent()
    }

    data class State(
        val items: List<TodoItem> = emptyList(),
        val text: String = ""
    )
}
