package de.darthkali.weefood.screens.recipe_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.darthkali.mocks.RecipeMock
import de.fhe.ai.weemeal.app.ui.screens.core.AppBar
import de.fhe.ai.weemeal.app.ui.screens.core.recipe_list.SearchAppBar
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RecipeListScreen(
//    recipeListState: RecipeListState,
//    navHostController: NavHostController,
//    onTriggerEvent: (RecipeListEvents) -> Unit,
//    onClickOpenRecipe: (Int) -> Unit,
//    onClickAddNewRecipe: () -> Unit
) {
    MaterialTheme(
//        displayProgressBar = recipeListState.isLoading,
    ) {
        Scaffold(
//            topBar = {
//                AppBar(title = "Rezeptliste")
//            },
//            bottomBar = { BottomBar(navController) },

            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { },
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = FloatingActionButtonDefaults.elevation(6.dp)
                ) {
                    Icon(Icons.Filled.Add, "")
                }
            }

        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column {
                    SearchAppBar(
                        query = "", // recipeListState.query,
                        onQueryChanged = {
//                            onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                        },
                        onExecuteSearch = {
//                            onTriggerEvent(RecipeListEvents.NewSearch)
                        },
                    )
                    LazyColumn {
                        itemsIndexed(
                            items = RecipeMock.recipeList
                        ) { index, recipe ->
                            Column() {
                                Row(verticalAlignment = Alignment.CenterVertically) {
//                                    CircleImage(
//                                        url = recipe.image ?: NO_IMAGE,
//                                        contentDescription = recipe.name
//                                    )
                                    Column {
                                        Text(
                                            text = recipe.name,
                                            modifier = Modifier
                                                .fillMaxWidth(0.85f)
                                                .wrapContentWidth(Alignment.Start)
                                                .padding(
                                                    top = 16.dp,
                                                    bottom = 16.dp,
                                                ),
                                            style = MaterialTheme.typography.h2
                                        )
//                                        if (recipe.cooking_time != 0) {
//                                            CustomChip(
//                                                text = "${recipe.cooking_time} ${recipe.cooking_time_unit}",
//                                                modifier = Modifier.padding(bottom = 16.dp)
//                                            )
//                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
