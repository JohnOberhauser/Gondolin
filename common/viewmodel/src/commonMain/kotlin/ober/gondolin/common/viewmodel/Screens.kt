package ober.gondolin.common.viewmodel

import ober.gondolin.common.navigation.Direction
import ober.gondolin.common.navigation.Directions
import ober.gondolin.common.navigation.Screen
import ober.gondolin.common.viewmodel.start.NewUserViewModel
import ober.gondolin.common.viewmodel.start.UnlockViewModel

sealed class TopLevelScreen: Screen() {

    open class Splash: TopLevelScreen() {
        protected object ToNewUserScreen: Directions<TopLevelScreen>(
            listOf(
                Direction.Pop(),
                Direction.Push(NewUserViewModel())
            )
        )
        protected object ToUnlockScreen: Directions<TopLevelScreen>(
            listOf(
                Direction.Pop(),
                Direction.Push(UnlockViewModel())
            )
        )
    }

    open class NewUser: TopLevelScreen() {
        protected object ToCategoriesScreen: Directions<TopLevelScreen>(
            listOf(
                Direction.Pop(),
                Direction.Push(Main())
            )
        )
    }

    open class Unlock: TopLevelScreen() {
        protected object ToCategoriesScreen: Directions<TopLevelScreen>(
            listOf(
                Direction.Pop(),
                Direction.Push(Main())
            )
        )
    }

    open class Main: TopLevelScreen()
}

sealed class MainLevelScreen: Screen() {
    open class Categories: MainLevelScreen()
}