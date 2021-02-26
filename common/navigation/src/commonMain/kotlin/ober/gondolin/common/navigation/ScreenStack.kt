package ober.gondolin.common.navigation

class ScreenStack<T : Screen> {

    private val stack = mutableListOf<T>()

    fun add(screen: T) {
        stack.add(screen)
    }

    fun remove(screen: T) {
        screen.clear()
        stack.remove(screen)
    }

    fun removeLast() {
        stack.lastOrNull()?.let { screen ->
            remove(screen)
        }
    }

    fun isEmpty(): Boolean = stack.isEmpty()

    fun contains(screen: T): Boolean = stack.contains(screen)

    fun reversed(): List<T> = stack.reversed()

    fun last(): T = stack.last()
}