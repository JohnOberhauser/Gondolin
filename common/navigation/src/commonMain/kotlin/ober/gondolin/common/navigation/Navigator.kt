package ober.gondolin.common.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class Navigator internal constructor(
    root: Screen
) {

    private val stack = mutableListOf<Screen>()
    var currentScreen = MutableStateFlow(root)

    init {
        stack.add(root)
    }

    fun navigate(directions: Directions) {
        for (direction in directions.directions) {
            when (direction) {
                is Direction.Push -> {
                    stack.add(direction.screen)
                }
                is Direction.Pop -> {
                    stack.removeLastOrNull()
                }
                is Direction.PopUpTo -> {
                    if (stack.isEmpty() || !stack.contains(direction.screen)) {
                        continue
                    }
                    for (screen in stack.reversed()) {
                        if (!direction.inclusive && direction.screen == screen) {
                            break
                        }
                        stack.remove(screen)
                        if (direction.screen == screen) {
                            break
                        }
                    }
                }
            }
        }

        if (stack.isEmpty()) throw EmptyStackException()

        currentScreen.value = stack.last()
    }

    /**
     * return true if the stack is empty after popping
     */
    fun navigateUp(): Boolean {
        stack.removeLastOrNull()
        return if (stack.isEmpty()) {
            true
        } else {
            currentScreen.value = stack.last()
            false
        }
    }

    class EmptyStackException: Exception()
}