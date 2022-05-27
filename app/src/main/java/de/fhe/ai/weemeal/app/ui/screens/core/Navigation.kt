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
import de.fhe.weefood.screens.recipe_list.RecipeDetailScreen
import de.fhe.weefood.screens.recipe_list.RecipeListScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

enum class Screens(val icon: ImageVector) {
    Recipe(Icons.Filled.Home),
    RecipeList(Icons.Filled.Home),
    ShoppingList(Icons.Filled.Home),
    WeekList(Icons.Filled.Home),
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
        startDestination = Screens.WeekList.name,
        modifier = modifier
    ) {
        composable(Screens.Recipe.name) {
            onNavigation(Screens.Recipe.name)
            // RecipeScreen() TODO: hier den Recipe Screen einfügen
        }
        composable(Screens.RecipeList.name) {
            onNavigation(Screens.RecipeList.name)
            RecipeListScreen()
        }
        composable(Screens.ShoppingList.name) {
            onNavigation(Screens.ShoppingList.name)
            // ShoppingList() TODO: hier den ShoppingList Screen einfügen
        }
        composable(Screens.WeekList.name) {
            onNavigation(Screens.WeekList.name)
            // WeekList() TODO: hier den WeekList Screen einfügen
        }
        composable(Screens.Settings.name) {
            onNavigation(Screens.Recipe.name)
            // Settings() TODO: hier den Settings Screen einfügen
        }
    }
}
