package de.fhe.ai.weemeal.app.ui.screens.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.androidx.compose.inject
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

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
        startDestination = Screen.WeekList.route,
        modifier = modifier
    ) {
        composable(Screen.WeekList.route) {
            val vm by viewModel<UserListScreenViewModel>()

            Screen.WeekList.prepareAppBarActions(vm)
            onNavigation(Screen.WeekList)

            UserListScreen(vm)
        }
        composable(
            Screen.UserDetail.route,
            Screen.UserDetail.navigationCommand(0).arguments
        ) { entry ->
            val userId = entry.arguments?.getLong("userId")
            val vm by viewModel<DetailScreenViewModel>(parameters = { parametersOf(userId) })

            Screen.UserDetail.prepareAppBarActions(LocalContext.current, vm)
            onNavigation(Screen.UserDetail)

            DetailScreen(vm)
        }
        composable(Screen.Map.route) {
            onNavigation(Screen.Map)
            MapScreen()
        }
        composable(Screen.Settings.route) {
            onNavigation(Screen.Settings)
            SettingsScreen()
        }
        composable(Screen.Input.route) {
            onNavigation(Screen.Input)
            InputScreen()
        }
    }
}
