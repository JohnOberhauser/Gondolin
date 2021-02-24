package ober.gondolin.common.navigation

sealed class Screen {

    object Splash: Screen() {
        fun navigateToNewUserScreen() {
            Navigator.navigator?.navigate(Direction.Push(NewUser))
        }
    }

    object NewUser: Screen() {
        fun navigateToCategoriesScreen() {
            Navigator.navigator?.navigate(
                Direction.Pop(upTo = Splash),
                Direction.Push(Categories)
            )
        }
    }

    object Categories: Screen()
}