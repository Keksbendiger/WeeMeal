package de.fhe.ai.weemeal.app.ui.screens.core

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi

val LocalNavCtrl = staticCompositionLocalOf<NavHostController> { error("no nav controller set") }
val LocalScaffoldState = staticCompositionLocalOf<ScaffoldState> { error("no scaffolded state set") }

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    var title by rememberSaveable { mutableStateOf(Screens.Main.name) }

    CompositionLocalProvider(
        LocalNavCtrl provides navController,
        LocalScaffoldState provides scaffoldState
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { AppBar(title) },
            bottomBar = { BottomBar(navController) }
        ) { innerPadding ->
            AppNavigationHost(
                onNavigation = { title = it },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
