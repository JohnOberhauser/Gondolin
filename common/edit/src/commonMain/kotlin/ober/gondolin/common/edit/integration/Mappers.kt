package ober.gondolin.common.edit.integration

import ober.gondolin.common.edit.TodoEdit.Model
import ober.gondolin.common.edit.store.TodoEditStore.State

internal val stateToModel: (State) -> Model =
    {
        Model(
            text = it.text,
            isDone = it.isDone
        )
    }
