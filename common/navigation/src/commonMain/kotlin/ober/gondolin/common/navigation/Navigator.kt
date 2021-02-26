package ober.gondolin.common.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class Navigator<T> internal constructor(
    root: T
) {

    private val stack = mutableListOf<T>()
    var currentScreen = MutableStateFlow(root)

    init {
        stack.add(root)
    }

    fun navigate(directions: Directions<T>) {
        for (direction in directions.directions) {
            when (direction) {
                is Direction.Push -> {
                    stack.add(direction.screen)
                }
                is Direction.Pop -> {
                    if (direction.screen == null) {
                        stack.removeLastOrNull()
                    } else {
                        if (stack.isEmpty() || !stack.contains(direction.screen)) {
                            continue
                        }
                        for (screen in stack.reversed()) {
                            if (direction.screen == screen) {
                                stack.remove(screen)
                                break
                            }
                        }
                    }
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