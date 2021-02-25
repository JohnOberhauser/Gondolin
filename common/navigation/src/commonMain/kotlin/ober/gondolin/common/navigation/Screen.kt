package ober.gondolin.common.navigation

sealed class Screen {

    object Splash: Screen() {
        object ToNewUserScreen: Directions(
            listOf(
                Direction.Pop,
                Direction.Push(NewUser)
            )
        )
        object ToUnlockScreen: Directions(
            listOf(
                Direction.Pop,
                Direction.Push(Unlock)
            )
        )
    }

    object NewUser: Screen() {
        object ToCategoriesScreen: Directions(
            listOf(
                Direction.Pop,
                Direction.Push(Categories)
            )
        )
    }

    object Unlock: Screen() {
        object ToCategoriesScreen: Directions(
            listOf(
                Direction.Pop,
                Direction.Push(Categories)
            )
        )
    }

    object Categories: Screen()
}