package de.fhe.weefood.screens.recipe_list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import de.fhe.ai.weemeal.mocks.RecipeMock
import de.fhe.ai.weemeal.recipe_list.CustomChip
import de.fhe.ai.weemeal.recipe_list.ListComponent
import de.fhe.ai.weemeal.recipe_list.SearchAppBar
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Preview
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RecipeDetailScreen(
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
                            items = RecipeMock.generateList()
                        ) { index, recipe ->
                            Column() {

                                Column(Modifier.fillMaxWidth()) {
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

                                    LazyRow {
                                        itemsIndexed(
                                            items = recipe.tags!!
                                        ) { _, tag ->
                                            CustomChip(
                                                text = tag.name,
                                                modifier = Modifier.padding(start = 5.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    ListComponent(
                                        textLeft = "timeActiveCooking",
                                        textRight = recipe.timeActiveCooking.toString()
                                    )

                                    ListComponent(
                                        textLeft = "timePreparation",
                                        textRight = recipe.timePreparation.toString()
                                    )

                                    ListComponent(
                                        textLeft = "timeOverall",
                                        textRight = recipe.timeOverall.toString()
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))

                                    ListComponent(
                                        textLeft = "defaultServings",
                                        textRight = recipe.defaultServings.toString()
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))

                                    recipe.defaultIngredients?.forEach {
                                        ListComponent(
                                            textLeft = it.name,
                                            textRight = it.quantity.quantity.toString() + "  " + it.quantity.unit
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(text = recipe.instructions ?: "")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}