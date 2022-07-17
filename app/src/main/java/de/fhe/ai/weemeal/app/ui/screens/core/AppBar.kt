package de.fhe.ai.weemeal.app.ui.screens.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(screen: Screen) {
    TopAppBar(
        title = {
            Text(text = screen.title)
        },
        navigationIcon = {
            Icon(
                screen.icon,
                screen.title,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        },
        actions = screen.appBarActions
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
