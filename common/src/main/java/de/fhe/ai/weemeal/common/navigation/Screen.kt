package de.fhe.ai.weemeal.common.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
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

//    TODO: Delete uneeded Screens after implementing your Navigation correctly.
//     Simply uncommenting can lead to app failure because of missing parameters ->
//     You can remove the "/{yourId}" part of the route and
//     the content (code) of the implementation {} of your Screen as workaround...
//     Example: ShoppingList
    Screen.ShoppingList
//    Screen.RecipeDetail,
//    Screen.RecipeEdit,
//    Screen.MealDetail
)

sealed class Screen(
    val title: String = "Title",
    val icon: ImageVector = Icons.Filled.Favorite,
    val route: String = "",
    val label: String = "", // Should only be implemented by RootScreens
    val hasBottomBar: Boolean = false
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
        title = "Mahlzeit",
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
        title = "Rezeptdetails",
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
        title = "Rezept anpassen",
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
        icon = Icons.Filled.List,
        route = "RecipeList"
    )

    object AddRecipeToWeekList : Screen(
        title = "Zu Wochenplan hinzufügen",
        route = "AddRecipeToWeekList/{cookingDateDaysAhead}"
    ) {
        override fun navigationCommand(vararg value: Any) = object : NavigationCommand {

            override val arguments = listOf(
                navArgument("cookingDateDaysAhead") {
                    type = NavType.LongType
                }
            )
            override val destination = "AddRecipeToWeekList/${value[0]}"
        }
    }

    object ShoppingListSelect : Screen(
        title = "Mahlzeiten für die Einkaufsliste wählen",
        icon = Icons.Filled.ShoppingCart,
        route = "ShoppingListSelect",
        label = "Einkaufsliste"
    )

    object ShoppingList : Screen(
        title = "Einkaufsliste",
        route = "ShoppingList" // /{shoppingListId}"
    )
//  {
//        override fun navigationCommand(vararg value: Any) = object : NavigationCommand {
//
//            override val arguments = listOf(
//                navArgument("shoppingListId") {
//                    type = NavType.LongType
//                }
//            )
//            override val destination = "ShoppingList/${value[0]}"
//        }
//  }

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
