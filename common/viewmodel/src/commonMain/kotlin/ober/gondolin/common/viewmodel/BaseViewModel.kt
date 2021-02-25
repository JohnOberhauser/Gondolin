package ober.gondolin.common.viewmodel

import ober.gondolin.common.navigation.NavigationModule
import ober.gondolin.common.navigation.Navigator
import org.kodein.di.instance

abstract class BaseViewModel {
    val navigator: Navigator by NavigationModule.di.instance()
}