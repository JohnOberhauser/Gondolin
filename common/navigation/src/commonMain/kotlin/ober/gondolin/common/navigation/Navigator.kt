package ober.gondolin.common.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class Navigator(
    root: Screen
) {

    private val stack = mutableListOf<Screen>()
    var currentScreen = MutableStateFlow(root)

    init {
        stack.add(root)
        navigator = this
    }

    fun navigate(vararg directions: Direction) {
        for (direction in directions) {
            when (direction) {
                is Direction.Push -> {
                    stack.add(direction.screen)
                }
                is Direction.Pop -> {
                    if (stack.isEmpty()) {
                        continue
                    }
                    for (screen in stack.reversed()) {
                        stack.remove(screen)
                        if (direction.upTo == null || direction.upTo == screen) {
                            break
                        }
                    }
                }
            }
        }

        currentScreen.value = stack.last()
    }

    companion object {
        var navigator: Navigator? = null
    }
}