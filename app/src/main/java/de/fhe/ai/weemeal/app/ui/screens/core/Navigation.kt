@file: Suppress("MatchingDeclarationName")

package de.fhe.ai.weemeal.app.ui.screens.core

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.fhe.ai.weemeal.mealDetail.MealDetailsScreen
import de.fhe.ai.weemeal.recipeDetail.RecipeDetailsScreen
import de.fhe.ai.weemeal.recipeDetail.RecipeEditScreen
import de.fhe.ai.weemeal.recipeList.RecipeListScreen
import de.fhe.ai.weemeal.shoppinglist.ShoppingListScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import de.fhe.ai.weemeal.shoppinglist.ShoppingListSelectScreen
import de.fhe.ai.weemeal.weeklistComponent.WeeklistScreen

enum class Screens(val icon: ImageVector) {
    Recipe(Icons.Filled.Home),
    RecipeList(Icons.Filled.Home),
    ShoppingListSelectScreen(Icons.Filled.Home),
    WeekList(Icons.Filled.Home),
    Settings(Icons.Filled.Settings),
    RecipeEdit(Icons.Filled.Edit),
    Meal(Icons.Filled.Favorite),
    ShoppingList(Icons.Filled.Home);
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
            RecipeDetailsScreen()
        }
        composable(Screens.RecipeList.name) {
            onNavigation(Screens.RecipeList.name)
            RecipeListScreen()
        }
        composable(Screens.ShoppingList.name) {
            onNavigation(Screens.ShoppingList.name)
            ShoppingListScreen()
            //TODO: hier den ShoppingList Screen einfügen
        }

        composable(Screens.ShoppingListSelectScreen.name) {
            onNavigation(Screens.ShoppingListSelectScreen.name)
            ShoppingListSelectScreen()
        }
        composable(Screens.WeekList.name) {
            onNavigation(Screens.WeekList.name)
            WeeklistScreen()
        }
        composable(Screens.Settings.name) {
            onNavigation(Screens.Recipe.name)
            // Settings() TODO: hier den Settings Screen einfügen
        }

        composable(Screens.Meal.name) {
            onNavigation(Screens.Meal.name)
            MealDetailsScreen()
        }
        composable(Screens.RecipeEdit.name) {
            onNavigation(Screens.RecipeEdit.name)
            RecipeEditScreen()
        }
    }
}
