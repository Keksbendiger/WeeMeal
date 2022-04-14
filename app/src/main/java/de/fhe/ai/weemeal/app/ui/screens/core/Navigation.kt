package de.fhe.ai.weemeal.app.ui.screens.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.fhe.ai.weemeal.app.Greeting

enum class Screens(val icon: ImageVector) {
    Main(Icons.Filled.Home),
    Settings(Icons.Filled.Settings);
}

@Composable
fun AppNavigationHost(
    onNavigation: (title: String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = LocalNavCtrl.current,
        startDestination = Screens.Main.name,
        modifier = modifier
    ) {
        composable(Screens.Main.name) {
            onNavigation(Screens.Main.name)
            Greeting(name = "hello")
        }
        composable(Screens.Settings.name) {
            onNavigation(Screens.Settings.name)
            Greeting(name = "settings")
        }
    }
}
