package de.fhe.ai.weemeal.app.ui.screens.core

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

val RootScreens = listOf(
    Screen.RecipeList,
    Screen.WeekList,
    Screen.ShoppingListSelect,
)

sealed class Screen(
    val title: String = "Title",
    val icon: ImageVector = Icons.Filled.Favorite,
    val route: String = "",
    val label: String = "" // Should only be implemented by RootScreens
) {
    var appBarActions: @Composable RowScope.() -> Unit = {}
        protected set

    open fun prepareAppBarActions(vararg values: Any) {}

    open fun navigationCommand(vararg value: Any) = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = route
    }

    object Undefined : Screen(
        title = "Undefined",
        icon = Icons.Filled.Warning,
        route = ""
    )

    object WeekList : Screen(
        title = "Wochenplan",
        icon = Icons.Filled.Home,
        route = "WeekList",
    ) {
//        override fun navigationCommand(vararg value: Any) = object : NavigationCommand {
//
//            override val arguments = listOf(
//                navArgument("mealId") {
//                    type = NavType.LongType
//                }
//            )
//            override val destination = "MealDetail/${value[0]}"
//        }
    }

    object MealDetail : Screen(
        title = "Mahlzeit", // TODO: Use meals name instead e.g. "Spaghetti mit grün soß"
        route = "MealDetail/{mealId}"
    ) {
        override fun navigationCommand(vararg value: Any) = object : NavigationCommand {

            override val arguments = listOf(
                navArgument("mealId") {
                    type = NavType.LongType
                }
            )
            override val destination = "MealDetail/${value[0]}"
        }
    }

    object RecipeDetail : Screen(
        title = "Rezeptdetails", // TODO: Use recipe name instead e.g. "Spaghetti mit grün soß"
        route = "RecipeDetail/{recipeId}"
    ) {
        override fun navigationCommand(vararg value: Any) = object : NavigationCommand {

            override val arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.LongType
                }
            )
            override val destination = "RecipeDetail/${value[0]}"
        }
    }

    object RecipeEdit : Screen(
        title = "Rezept anpassen", // TODO: Use "Bearbeite " + recipeName. e.g. "Bearbeite Spaghetti mit grün soß"
        route = "RecipeEdit/{recipeId}"
    ) {
        override fun navigationCommand(vararg value: Any) = object : NavigationCommand {

            override val arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.LongType
                }
            )
            override val destination = "RecipeEdit/${value[0]}"
        }
        // TODO: Handle Backstack because this screen can be reached via Meal or Recipe and we
        //         want to be able to go back accordingly...
    }

    object RecipeList : Screen(
        title = "Rezepte",
        icon = Icons.Filled.Star, // TODO: call drawable menu_book instead
        route = "RecipeList"
    )

    object ShoppingListSelect : Screen(
        title = "Mahlzeiten für die Einkaufsliste wählen",
        icon = Icons.Filled.ShoppingCart,
        route = "ShoppingListSelect",
        label = "Einkaufsliste"
    )

    object ShoppingList : Screen(
        title = "Einkaufsliste",
        route = "ShoppingList/{shoppingListId}"
    ) {
        override fun navigationCommand(vararg value: Any) = object : NavigationCommand {

            override val arguments = listOf(
                navArgument("shoppingListId") {
                    type = NavType.LongType
                }
            )
            override val destination = "ShoppingList/${value[0]}"
        }
    }

    object Settings : Screen(
        title = "Settings",
        icon = Icons.Filled.Settings,
        route = "Settings"
    )
}

//    {
//        override fun prepareAppBarActions(vararg values: Any) {
//            if (values[0] !is Context)
//                error("First Parameter must be of type *Context*")
//            if (values[1] !is DetailScreenViewModel)
//                error("Second Parameter must be of type *DetailScreenViewModel")
//
//            val context = values[0] as Context
//            val viewModel = values[1] as DetailScreenViewModel
//
//            appBarActions = {
//                IconButton(
//                    onClick = { viewModel.share(context) }
//                ) {
//                    Icon(Icons.Filled.Share, contentDescription = null)
//                }
//            }
//        }