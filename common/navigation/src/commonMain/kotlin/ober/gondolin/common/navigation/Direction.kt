package ober.gondolin.common.navigation

sealed class Direction<T> {

    data class Push<Y>(val screen: Y): Direction<Y>()

    data class Pop<Y>(val screen: Y? = null): Direction<Y>()

    data class PopUpTo<Y>(val screen: Y, val inclusive: Boolean = false): Direction<Y>()
}

abstract class Directions<T>(
    val directions: List<Direction<T>>
)