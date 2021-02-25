package ober.gondolin.common.navigation

sealed class Direction {

    data class Push(val screen: Screen): Direction()

    object Pop: Direction()

    data class PopUpTo(val screen: Screen, val inclusive: Boolean = false): Direction()
}

abstract class Directions(
    val directions: List<Direction>
)