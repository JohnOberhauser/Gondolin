package ober.gondolin.common.navigation

sealed class Direction {

    data class Push(val screen: Screen): Direction()

    data class Pop(val upTo: Screen? = null): Direction()
}