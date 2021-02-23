package ober.gondolin.common.navigation

import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.Parcelize

sealed class Screen : Parcelable {

    @Parcelize
    object Splash: Screen() {
        fun navigateToNewUserScreen() {
            Navigator.navigator?.navigate(Direction.Push(NewUser))
        }
    }

    @Parcelize
    object NewUser: Screen()
}