package ober.gondolin.common.navigation

sealed class Screen {

    object Splash: Screen() {
        object ToNewUserScreen: Directions(
            listOf(
                Direction.Push(NewUser)
            )
        )
    }

    object NewUser: Screen() {
        object ToCategoriesScreen: Directions(
            listOf(
                Direction.Pop(upTo = Splash),
                Direction.Push(Categories)
            )
        )
    }

    object Categories: Screen()
}