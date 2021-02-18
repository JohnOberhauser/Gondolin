package ober.gondolin.common.edit.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.badoo.reaktive.base.invoke
import ober.gondolin.common.edit.TodoEdit
import ober.gondolin.common.edit.TodoEdit.Dependencies
import ober.gondolin.common.edit.TodoEdit.Model
import ober.gondolin.common.edit.TodoEdit.Output
import ober.gondolin.common.edit.store.TodoEditStore.Intent
import ober.gondolin.common.edit.store.TodoEditStoreProvider
import ober.gondolin.common.utils.asValue
import ober.gondolin.common.utils.getStore

internal class TodoEditImpl(
    componentContext: ComponentContext,
    dependencies: Dependencies
) : TodoEdit, ComponentContext by componentContext, Dependencies by dependencies {

    private val store =
        instanceKeeper.getStore {
            TodoEditStoreProvider(
                storeFactory = storeFactory,
                database = TodoEditStoreDatabase(queries = database.todoDatabaseQueries),
                id = itemId
            ).provide()
        }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun onTextChanged(text: String) {
        store.accept(Intent.SetText(text = text))
    }

    override fun onDoneChanged(isDone: Boolean) {
        store.accept(Intent.SetDone(isDone = isDone))
    }

    override fun onCloseClicked() {
        editOutput(Output.Finished)
    }
}
