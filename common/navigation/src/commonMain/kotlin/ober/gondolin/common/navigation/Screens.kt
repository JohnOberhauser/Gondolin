package ober.gondolin.common.navigation

sealed class TopLevelScreen {

    object Splash: TopLevelScreen() {
        object ToNewUserScreen: Directions<TopLevelScreen>(
            listOf(
                Direction.Pop(),
                Direction.Push(NewUser)
            )
        )
        object ToUnlockScreen: Directions<TopLevelScreen>(
            listOf(
                Direction.Pop(),
                Direction.Push(Unlock)
            )
        )
    }

    object NewUser: TopLevelScreen() {
        object ToCategoriesScreen: Directions<TopLevelScreen>(
            listOf(
                Direction.Pop(),
                Direction.Push(Main)
            )
        )
    }

    object Unlock: TopLevelScreen() {
        object ToCategoriesScreen: Directions<TopLevelScreen>(
            listOf(
                Direction.Pop(),
                Direction.Push(Main)
            )
        )
    }

    object Main: TopLevelScreen()
}

sealed class MainLevelScreen {
    object CategoriesScreen: MainLevelScreen()
}