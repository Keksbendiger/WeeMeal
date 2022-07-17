package de.fhe.ai.weemeal.app.ui.screens.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

val LocalScaffoldState = staticCompositionLocalOf<ScaffoldState> { error("no scaffolded state set") }

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Undefined) }

    CompositionLocalProvider(LocalScaffoldState provides scaffoldState) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { AppBar(currentScreen) },
            bottomBar = { BottomBar(navController) }
        ) { innerPadding ->
            AppNavigationHost(
                navController,
                onNavigation = {
                    currentScreen = it
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
