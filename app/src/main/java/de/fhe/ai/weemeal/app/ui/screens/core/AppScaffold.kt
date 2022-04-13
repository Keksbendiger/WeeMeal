package de.fhe.ai.weemeal.app.ui.screens.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val LocalNavCtrl = staticCompositionLocalOf<NavHostController> { error("no nav controller set") }
val LocalScaffoldState = staticCompositionLocalOf<ScaffoldState> { error("no scaffolded state set") }

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    var title by rememberSaveable { mutableStateOf( Screens.Main.name ) }

    CompositionLocalProvider(
        LocalNavCtrl provides navController,
        LocalScaffoldState provides scaffoldState) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { AppBar(title) },
            bottomBar = { BottomBar(navController) }
        ) { innerPadding ->
            AppNavigationHost(
                onNavigation = { title = it },
                modifier = Modifier.padding( innerPadding )
            )
        }
    }
}
