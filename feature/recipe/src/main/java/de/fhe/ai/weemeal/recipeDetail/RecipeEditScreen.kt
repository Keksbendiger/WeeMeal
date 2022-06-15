package de.fhe.ai.weemeal.recipeDetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.common.components.CustomChip
import de.fhe.ai.weemeal.common.components.RecipeNumberInput
import de.fhe.ai.weemeal.common.components.RecipeStringInput
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import de.fhe.ai.weemeal.mocks.RecipeMock
import de.fhe.ai.weemeal.recipe.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Preview
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RecipeEditScreen(
//    recipeListState: RecipeListState,
//    navHostController: NavHostController,
//    onTriggerEvent: (RecipeListEvents) -> Unit,
//    onClickOpenRecipe: (Int) -> Unit,
//    onClickAddNewRecipe: () -> Unit
) {
    WeeMealTheme {
        Scaffold(
//            topBar = {
//                AppBar(title = "Rezeptansicht")
//            },
//            bottomBar = { BottomBar(navController) },
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                val recipe = RecipeMock.generateRecipe()

                Column(
                    Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    // Image
                    // TODO content description / Localization
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                        ) {
                            Image(
                                painterResource(id = recipe.image),
                                contentDescription = "Dummy-Image",
                                modifier = Modifier.size(120.dp)
                            )

                            IconButton(onClick = { /*TODO*/ }, Modifier.align(Alignment.Center)) {
                                Icon(
                                    Icons.Filled.Edit,
                                    "",
                                    modifier = Modifier
                                        .size(20.dp)
                                        .background(
                                            MaterialTheme.colors.background,
                                            shape = CircleShape
                                        )
                                        .padding(2.dp),
                                )
                            }
                        }
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
                        item {
                            IconButton(onClick = { /*TODO add Tag*/ }) {
                                Icon(Icons.Filled.Add, "Add Tag Button")
                            }
                        }
                    }

                    // Ingredients
                    Row {
                        Text(
                            text = "Zutaten",
                            modifier = Modifier
                                .wrapContentWidth(Alignment.Start)
                                .padding(
                                    top = 8.dp,
                                    bottom = 2.dp,
                                ),
                            style = MaterialTheme.typography.h1
                        )

                        Text(
                            text = " für ",
                            modifier = Modifier
                                .wrapContentWidth(Alignment.Start)
                                .padding(
                                    top = 16.dp,
                                ),
                        )

                        RecipeNumberInput(
                            value = recipe.defaultServings.toString(),
                            onValueChange = {
                                if (it.toInt() > 0) recipe.defaultServings = it.toInt()
                            },
                            Modifier.align(Alignment.Bottom)
                        )

                        Text(
                            text = " Portionen",
                            modifier = Modifier
                                .wrapContentWidth(Alignment.Start)
                                .padding(
                                    top = 16.dp,
                                ),
                        )
                    }

                    Column {
                        recipe.defaultIngredients?.forEach {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                var ingredient = it

                                IconButton(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier
                                        .size(20.dp)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Icon(Icons.Filled.Delete, "delete ingredient")
                                }

                                RecipeStringInput(
                                    value = ingredient.name,
                                    onValueChange = {
                                        ingredient.name = it
                                    },
                                    wide = true
                                )

                                Row(horizontalArrangement = Arrangement.End) {

                                    RecipeNumberInput(
                                        value = ingredient.quantity.quantity.toString(),
                                        onValueChange = { ingredient.quantity.quantity }
                                    )

                                    RecipeStringInput(
                                        value = ingredient.quantity.unit,
                                        onValueChange = {
                                            ingredient.quantity.unit = it
                                        }
                                    )
                                }
                            }
                        }
                        Row() {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .size(20.dp)
                            ) {
                                Icon(Icons.Filled.Add, "add ingredient")
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // cooking time
                    Text(
                        // TODO Localization
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
                                painterResource(id = R.drawable.outdoor_grill),
                                contentDescription = "active cooking time"
                            )
                            Text("Kochen")

                            Row(horizontalArrangement = Arrangement.End) {
                                RecipeNumberInput(
                                    value = recipe.timeActiveCooking?.value.toString(),
                                    onValueChange = {
                                        recipe.timeActiveCooking!!.value = it.toFloat()
                                    }
                                )
                                RecipeStringInput(
                                    value = recipe.timeActiveCooking?.unit ?: "",
                                    onValueChange = {
                                        recipe.timeActiveCooking!!.unit = it
                                    }
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painterResource(id = R.drawable.set_meal),
                                contentDescription = "preparation time"
                            )
                            Text("Vorbereitung")

                            Row(horizontalArrangement = Arrangement.End) {
                                RecipeNumberInput(
                                    value = recipe.timePreparation?.value.toString(),
                                    onValueChange = {
                                        recipe.timePreparation!!.value = it.toFloat()
                                    }
                                )
                                RecipeStringInput(
                                    value = recipe.timePreparation?.unit ?: "",
                                    onValueChange = {
                                        recipe.timePreparation!!.unit = it
                                    }
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painterResource(id = R.drawable.access_time),
                                contentDescription = "overall time"
                            )
                            Text("Gesamt")

                            Row(horizontalArrangement = Arrangement.End) {
                                RecipeNumberInput(
                                    value = recipe.timeOverall?.value.toString(),
                                    onValueChange = {
                                        recipe.timeOverall!!.value = it.toFloat()
                                    }
                                )
                                RecipeStringInput(
                                    value = recipe.timeOverall?.unit ?: "",
                                    onValueChange = {
                                        recipe.timeOverall!!.unit = it
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        // TODO Localization
                        text = "Zubereitung",
                        modifier = Modifier
                            .wrapContentWidth(Alignment.Start)
                            .padding(
                                top = 4.dp,
                                bottom = 2.dp,
                            ),
                        style = MaterialTheme.typography.h1
                    )
                    TextField(value = recipe.instructions ?: "", onValueChange = { recipe.instructions })
                }
            }
        }
    }
}
