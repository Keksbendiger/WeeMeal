package de.fhe.ai.weemeal.app.ui.screens.core

import androidx.navigation.NamedNavArgument
import kotlinx.coroutines.flow.MutableStateFlow

interface NavigationCommand {
    val destination: String
    val arguments: List<NamedNavArgument>
}

val EmptyDestination = object : NavigationCommand {
    override val arguments = emptyList<NamedNavArgument>()
    override val destination = ""
}

val GoBackDestination = object : NavigationCommand {
    override val arguments = emptyList<NamedNavArgument>()
    override val destination = "go_back"
}

class NavigationManager {
    var commands = MutableStateFlow(EmptyDestination)

    fun navigate(navCommand: NavigationCommand) {
        commands.value = navCommand
    }
}
