package ober.gondolin.common.root.integration

import com.arkivanov.decompose.value.Value
import com.badoo.reaktive.base.Consumer
import ober.gondolin.common.main.TodoMain
import ober.gondolin.common.main.TodoMain.Model
import ober.gondolin.common.main.TodoMain.Output

class TodoMainFake(
    val output: Consumer<Output>
) : TodoMain {

    override val models: Value<Model> get() = TODO("Not used")

    override fun onItemClicked(id: Long) {
    }

    override fun onItemDoneChanged(id: Long, isDone: Boolean) {
    }

    override fun onItemDeleteClicked(id: Long) {
    }

    override fun onInputTextChanged(text: String) {
    }

    override fun onAddItemClicked() {
    }
}