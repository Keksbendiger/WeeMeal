package de.fhe.ai.weemeal.mealDetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import de.fhe.ai.weemeal.common.components.CustomChip
import de.fhe.ai.weemeal.common.components.ListComponent
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun MealDetailsScreen(
    vm: MealDetailsViewModel,
) {
    WeeMealTheme {
        Scaffold(
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { vm.navigateToRecipeDetails() },
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
                            painterResource(id = vm.state.value.recipe.image),
                            contentDescription = "Dummy-Image",
                            modifier = Modifier.size(120.dp)
                        )
                    }

                    // Recipe Name
                    Text(
                        text = vm.state.value.recipe.name,
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
                            items = vm.state.value.recipe.tags!!
                        ) { _, tag ->
                            CustomChip(
                                text = tag.name,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                    }

                    // Cook Color
                    CookColorRow(vm)

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
                                if (vm.state.value.servings!! > 1) {
                                    vm.decreaseServings()
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
                            text = vm.state.value.servings.toString(),
                            modifier = Modifier
                                .padding(
                                    top = 8.dp,
                                    bottom = 2.dp,
                                ),
                            style = MaterialTheme.typography.h1
                        )

                        IconButton(
                            onClick = {
                                vm.increaseServings()
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
                        vm.state.value.recipe.defaultIngredients?.forEach {
                            ListComponent(
                                textLeft = it.name,
                                textRight =
                                (
                                    (it.quantity.quantity * vm.state.value.servingsRatio)
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
                            Text(vm.state.value.recipe.timeActiveCooking.toString())
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
                            Text(vm.state.value.recipe.timePreparation.toString())
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
                            Text(vm.state.value.recipe.timeOverall.toString())
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
                    Text(text = vm.state.value.recipe.instructions ?: "")
                }
            }
        }
    }
}

// @Preview
@Composable
fun CookColorRow(vm: MealDetailsViewModel) {
    var meal = vm.state.value
    // var meal = MealMock.generateSingleObject()

    Row {
        Text(
            text = "Farbe",
            modifier = Modifier
                .wrapContentWidth(Alignment.Start)
                .padding(
                    top = 4.dp,
                    bottom = 2.dp,
                ),
            style = MaterialTheme.typography.h1
        )

        IconButton(
            onClick = {
                vm.decreaseColor()
            }
        ) {
            Icon(
                painterResource(
                    id =
                    de.fhe.ai.weemeal.meal.R.drawable.ic_baseline_remove_circle_24,
                ),
                contentDescription = "color left button"
            )
        }

        Text(
            text = meal.cookColor.name,
            modifier = Modifier
                .width(200.dp)
                .padding(
                    top = 8.dp,
                    bottom = 2.dp,
                ).border(2.dp, Color(meal.cookColor.color.toColorInt()), RectangleShape),
            style = MaterialTheme.typography.h1,
        )

        IconButton(
            onClick = {
                vm.increaseColor()
            }
        ) {
            Icon(
                painterResource(
                    id =
                    de.fhe.ai.weemeal.meal.R.drawable.ic_baseline_add_circle_24,
                ),
                contentDescription = "color right button"
            )
        }
    }
}
