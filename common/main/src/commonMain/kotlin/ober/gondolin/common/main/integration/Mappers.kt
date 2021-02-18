package ober.gondolin.common.main.integration

import ober.gondolin.common.main.TodoMain.Model
import ober.gondolin.common.main.store.TodoMainStore.State

internal val stateToModel: (State) -> Model =
    {
        Model(
            items = it.items,
            text = it.text
        )
    }