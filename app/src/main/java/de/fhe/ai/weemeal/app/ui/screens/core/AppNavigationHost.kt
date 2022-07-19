package de.fhe.ai.weemeal.app.ui.screens.core

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.common.navigation.Screen
import de.fhe.ai.weemeal.mealDetail.MealDetailsScreen
import de.fhe.ai.weemeal.mealDetail.MealDetailsViewModel
import de.fhe.ai.weemeal.recipeDetail.RecipeDetailsScreen
import de.fhe.ai.weemeal.recipeDetail.RecipeEditScreen
import de.fhe.ai.weemeal.recipeDetail.RecipeEditViewModel
import de.fhe.ai.weemeal.recipeList.RecipeListScreen
import de.fhe.ai.weemeal.recipeList.RecipeListViewModel
import de.fhe.ai.weemeal.shoppinglist.ShoppingListScreen
import de.fhe.ai.weemeal.shoppinglist.ShoppingListSelectScreen
import de.fhe.ai.weemeal.weeklistComponent.WeeklistScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.inject
import org.koin.androidx.compose.viewModel
import de.fhe.ai.weemeal.weeklistComponent.WeekListViewModel

@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AppNavigationHost(
    navCtrl: NavHostController,
    onNavigation: (screen: Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationManager by inject<NavigationManager>()

    navigationManager.commands.collectAsState().value.also { command ->
        if (command.destination.isNotEmpty())
            if (command.destination == "go_back")
                navCtrl.popBackStack()
            else
                navCtrl.navigate(command.destination)
    }

    NavHost(
        navController = navCtrl,
        startDestination = Screen.WeekList.route, // Home Screen
        modifier = modifier
    ) {
        composable(Screen.WeekList.route,
            Screen.WeekList.navigationCommand(0).arguments) {
            val vm by viewModel<WeekListViewModel>()

            onNavigation(Screen.WeekList)

            WeeklistScreen(vm) // TODO: add viewmodel here!
        }

        composable(
            Screen.MealDetail.route,
            Screen.MealDetail.navigationCommand(0).arguments
        ) { // entry ->
//            val mealId = entry.arguments?.getLong("mealId")
            val vm by viewModel<MealDetailsViewModel>()

            onNavigation(Screen.MealDetail)

            MealDetailsScreen(vm)
        }

        composable(
            Screen.RecipeDetail.route,
            Screen.RecipeDetail.navigationCommand(0).arguments
        ) { // entry ->
//            val recipeId = entry.arguments?.getLong("recipeId")
//            val vm by viewModel<RecipeDetailViewModel>(parameters = { parametersOf(recipeId) })

            onNavigation(Screen.RecipeDetail)

            RecipeDetailsScreen() // TODO: add viewmodel here!
        }

        composable(
            Screen.RecipeEdit.route,
            Screen.RecipeEdit.navigationCommand(0).arguments
        ) { // entry ->
//            val recipeId = entry.arguments?.getLong("recipeId")
            val vm by viewModel<RecipeEditViewModel>()

            onNavigation(Screen.RecipeEdit)

            RecipeEditScreen(vm)
        }

        composable(Screen.RecipeList.route) {
            val vm by viewModel<RecipeListViewModel>()

            onNavigation(Screen.RecipeList)

            RecipeListScreen(vm)
        }

        composable(Screen.ShoppingListSelect.route) {
//            val vm by viewModel<ShoppingListSelectViewModel>()

            onNavigation(Screen.ShoppingListSelect)

            ShoppingListSelectScreen() // TODO: add viewmodel here!
        }

        composable(
            Screen.ShoppingList.route,
            Screen.ShoppingList.navigationCommand(0).arguments
        ) { // entry ->
//            val shoppingListId = entry.arguments?.getLong("shoppingListId")
//            val vm by viewModel<ShoppingListViewModel>()

            onNavigation(Screen.ShoppingList)

            ShoppingListScreen() // TODO: add viewmodel here!
        }

//        composable(Screen.Settings.route) {
//            onNavigation(Screen.Settings)
//            SettingsScreen()
//        }
    }
}

//            Screen.WeekList.prepareAppBarActions(vm)
