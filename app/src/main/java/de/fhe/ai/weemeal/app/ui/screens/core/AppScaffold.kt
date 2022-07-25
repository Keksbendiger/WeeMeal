package de.fhe.ai.weemeal.app.ui.screens.core

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import de.fhe.ai.weemeal.common.navigation.RootScreens
import de.fhe.ai.weemeal.common.navigation.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi

val LocalScaffoldState =
    staticCompositionLocalOf<ScaffoldState> { error("no scaffolded state set") }

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Undefined) }

    // State of bottomBar, set state to false, if current page route is not in RootScreens
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }


    CompositionLocalProvider(LocalScaffoldState provides scaffoldState) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { AppBar(currentScreen) },

            bottomBar = { BottomBar(navController, bottomBarState = bottomBarState) }

//            Not sufficient... Creates ugly recomposes over the whole screen and you shall never
//            be able to leave a Screen without bottomnav via back again :(
//            {
//                if (currentScreen in RootScreens) {
//                    BottomBar(navController)
//                }
//            }

        ) { innerPadding ->
            AppNavigationHost(
                navController,
                onNavigation = {
                    // Only RootScreens shall show Bottombar
                    bottomBarState.value = currentScreen in RootScreens

                    currentScreen = it
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}


/*
BottomBarAnimationTheme {
    val navController = rememberNavController()

    // Subscribe to navBackStackEntry, required to get current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    // Control TopBar and BottomBar
    when (navBackStackEntry?.destination?.route) {
        "cars" -> {
            // Show BottomBar and TopBar
            bottomBarState.value = true
            topBarState.value = true
        }
        "bikes" -> {
            // Show BottomBar and TopBar
            bottomBarState.value = true
            topBarState.value = true
        }
        "settings" -> {
            // Show BottomBar and TopBar
            bottomBarState.value = true
            topBarState.value = true
        }
        "car_details" -> {
            // Hide BottomBar and TopBar
            bottomBarState.value = false
            topBarState.value = false
        }
    }

    com.google.accompanist.insets.ui.Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                bottomBarState = bottomBarState
            )
        },
        topBar = {
            TopBar(
                navController = navController,
                topBarState = topBarState
            )
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.Cars.route,
            ) {
                composable(NavigationItem.Cars.route) {
                    // show BottomBar and TopBar
                    LaunchedEffect(Unit) {
                        bottomBarState.value = true
                        topBarState.value = true
                    }
                    CarsScreen(
                        navController = navController,
                    )
                }
                composable(NavigationItem.Bikes.route) {
                    // show BottomBar and TopBar
                    LaunchedEffect(Unit) {
                        bottomBarState.value = true
                        topBarState.value = true
                    }
                    BikesScreen(
                        navController = navController
                    )
                }
                composable(NavigationItem.Settings.route) {
                    // show BottomBar and TopBar
                    LaunchedEffect(Unit) {
                        bottomBarState.value = true
                        topBarState.value = true
                    }
                    SettingsScreen(
                        navController = navController,
                    )
                }
                composable(NavigationItem.CarDetails.route) {
                    // hide BottomBar and TopBar
                    LaunchedEffect(Unit) {
                        bottomBarState.value = false
                        topBarState.value = false
                    }
                    CarDetailsScreen(
                        navController = navController,
                    )
                }
            }
        }
}
 */