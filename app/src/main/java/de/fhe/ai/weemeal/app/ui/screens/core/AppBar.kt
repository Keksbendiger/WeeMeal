package de.fhe.ai.weemeal.app.ui.screens.core

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppBar(screen: Screen) {
    TopAppBar(
        title = {
            Text(text = screen.title)
        }

//    TODO: Add Backarrow and prepare appBarActions for going Back in Backstack

//        ,navigationIcon = {
//            Icon(
//                screen.icon,
//                screen.title,
//                modifier = Modifier.padding(horizontal = 12.dp)
//            )
//        },
//        actions = screen.appBarActions
    )
}

@Preview()
@Composable
fun WeekListAppBar() {
    AppBar(screen = Screen.WeekList)
}

@Preview()
@Composable
fun RecipeListAppBar() {
    AppBar(screen = Screen.RecipeList)
}

@Preview()
@Composable
fun ShoppingListSelectAppBar() {
    AppBar(screen = Screen.ShoppingListSelect)
}
