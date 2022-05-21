package de.fhe.ai.weemeal.app.ui.screens.core

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.fhe.weefood.screens.recipe_list.RecipeListScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

enum class Screens(val icon: ImageVector) {
    Main(Icons.Filled.Home),
    Settings(Icons.Filled.Settings);
}

@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
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
            RecipeListScreen()
        }
        composable(Screens.Settings.name) {
            onNavigation(Screens.Settings.name)
        }
    }
}
