package de.fhe.ai.weemeal.mealDetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.common.components.CustomChip
import de.fhe.ai.weemeal.common.components.ListComponent
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

// @Preview
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun MealDetailsScreen(
    mealDetailsViewModel: MealDetailsViewModel,
    mealId: Long?,
//    navHostController: NavHostController,
//    onTriggerEvent: (RecipeListEvents) -> Unit,
//    onClickOpenRecipe: (Int) -> Unit,
//    onClickAddNewRecipe: () -> Unit
) {
    WeeMealTheme {
        val meal = mealDetailsViewModel.state.value
        val recipe = meal.recipe

        Scaffold(
//            topBar = {
//                AppBar(title = "Rezeptansicht")
//            },
//            bottomBar = { BottomBar(navController) },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { mealDetailsViewModel.navigateToRecipeDetails(recipe.internalId) },
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = FloatingActionButtonDefaults.elevation(6.dp)
                ) {
                    Icon(Icons.Filled.Search, "")
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    // Image
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(id = recipe.image),
                            contentDescription = "Dummy-Image",
                            modifier = Modifier.size(120.dp)
                        )
                    }

                    // Recipe Name
                    Text(
                        text = recipe.name,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start)
                            .padding(
                                top = 8.dp,
                                bottom = 8.dp,
                            ),
                        style = MaterialTheme.typography.h3
                    )

                    // Tags
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

                    // Ingredients
                    Row {
                        Text(
                            text = "Zutaten für",
                            modifier = Modifier
                                .wrapContentWidth(Alignment.Start)
                                .padding(
                                    top = 8.dp,
                                    bottom = 2.dp,
                                ),
                            // style = MaterialTheme.typography.h1
                        )
                        // Servings
                        IconButton(
                            onClick = {
                                if (meal.servings!! > 1) {
                                    mealDetailsViewModel.decreaseServings()
                                }
                            }
                        ) {
                            Icon(
                                painterResource(
                                    id =
                                    de.fhe.ai.weemeal.meal.R.drawable.ic_baseline_remove_circle_24,
                                ),
                                contentDescription = "decrease button"
                            )
                        }

                        Text(
                            text = meal.servings.toString(),
                            modifier = Modifier
                                .padding(
                                    top = 8.dp,
                                    bottom = 2.dp,
                                ),
                            style = MaterialTheme.typography.h1
                        )

                        IconButton(
                            onClick = {
                                mealDetailsViewModel.increaseServings()
                            }
                        ) {
                            Icon(
                                painterResource(
                                    id =
                                    de.fhe.ai.weemeal.meal.R.drawable.ic_baseline_add_circle_24,
                                ),
                                contentDescription = "increase button"
                            )
                        }

                        Text(
                            text = "Portionen",
                            modifier = Modifier
                                .padding(
                                    top = 8.dp,
                                    bottom = 2.dp,
                                ),
                            // style = MaterialTheme.typography.h1
                        )
                    }

                    Column {
                        recipe.defaultIngredients?.forEach {
                            ListComponent(
                                textLeft = it.name,
                                textRight =
                                (
                                    (it.quantity.quantity * mealDetailsViewModel.state.value.servingsRatio)
                                        .toString()
                                    ) + "  " + it.quantity.unit
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // cooking time
                    Text(
                        text = "Kochzeiten",
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start)
                            .padding(
                                top = 4.dp,
                                bottom = 2.dp,
                            ),
                        style = MaterialTheme.typography.h1
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // TODO different icons
                            Icon(
                                painterResource(
                                    id = de.fhe.ai.weemeal.meal.R.drawable.outdoor_grill
                                ),
                                contentDescription = "active cooking time"
                            )
                            Text("Kochen")
                            Text(recipe.timeActiveCooking.toString())
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painterResource(
                                    id = de.fhe.ai.weemeal.meal.R.drawable.set_meal
                                ),
                                contentDescription = "preparation time"
                            )
                            Text("Vorbereitung")
                            Text(recipe.timePreparation.toString())
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painterResource(
                                    id = de.fhe.ai.weemeal.meal.R.drawable.access_time
                                ),
                                contentDescription = "overall time"
                            )
                            Text("Gesamt")
                            Text(recipe.timeOverall.toString())
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Zubereitung",
                        modifier = Modifier
                            .wrapContentWidth(Alignment.Start)
                            .padding(
                                top = 4.dp,
                                bottom = 2.dp,
                            ),
                        style = MaterialTheme.typography.h1
                    )
                    Text(text = recipe.instructions ?: "")
                }
            }
        }
    }
}
