package ober.gondolin.common.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class Navigator internal constructor(
    root: Screen
) {

    val stack = mutableListOf<Screen>()
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
                    if (stack.isEmpty() || (direction.upTo != null && !stack.contains(direction.upTo))) {
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

        if (stack.isEmpty()) throw EmptyStackException()

        currentScreen.value = stack.last()
    }

    /**
     * return true if the stack is empty after popping
     */
    fun navigateUp(): Boolean {
        stack.removeLast()
        return if (stack.isEmpty()) {
            true
        } else {
            currentScreen.value = stack.last()
            false
        }
    }

    class EmptyStackException: Exception()
}