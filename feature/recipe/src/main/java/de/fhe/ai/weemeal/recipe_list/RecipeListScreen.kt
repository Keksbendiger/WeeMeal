package de.fhe.weefood.screens.recipe_list

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.mocks.RecipeMock
import de.fhe.ai.weemeal.recipe_list.CustomChip
import de.fhe.ai.weemeal.recipe_list.ListComponent
import de.fhe.ai.weemeal.recipe_list.SearchAppBar
import kotlinx.coroutines.ExperimentalCoroutinesApi

//@Preview
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
    WeeMealTheme(
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
                    Icon(Filled.Add, "")
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
                    var recipes: List<Recipe>? = RecipeMock.generateList()

//                  Nullcheck -> TODO: More elegant way possible?
                    recipes?.let {
                        RecipeList(recipes)
                    } ?: kotlin.run {
//                        TODO: String ressource location correct?
                        Text(stringResource(de.fhe.ai.weemeal.recipe.R.string.no_recipes))
                    }
                }
            }
        }
    }
}

@Composable
private fun RecipeList(recipes: List<Recipe>) {
    LazyColumn {
        itemsIndexed(
            items = recipes
        ) { index, recipe ->
            RecipeListItem(recipe = recipe)
        }
    }
}

@Composable
private fun RecipeListItem(recipe: Recipe) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        RecipeListItemContent(recipe = recipe)
    }
}

@Composable
private fun RecipeListItemContent(recipe: Recipe) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column( modifier= Modifier
            .padding(12.dp)
            .weight(1f)
        ) {
            Row(modifier = Modifier
                    .fillMaxWidth()
            ){
                //            TODO: Use Image of Food/Recipe
                Icon(
                    imageVector = Filled.Lock,
                    contentDescription = "Dummy-Image",
                    modifier = Modifier
                        .fillMaxHeight())
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
//                TODO: Use better Icons -> ExpandLess and ExpandMore
                        imageVector = if (expanded) Filled.Close else Filled.Search,
                        contentDescription = if (expanded) {
                            "show less"
                        } else {
                            "show more"
                        }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            if (expanded) {
                RecipeListItemContentExpanded(recipe = recipe)
            }
        }
    }
}

@Composable
private fun RecipeListItemContentExpanded(recipe: Recipe) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = "Some Recipe Info")
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
    }
}

@Preview
@Composable
fun DefaultPreview() {
    WeeMealTheme {
        RecipeList(recipes = RecipeMock.generateList())
    }
}

//@Preview
//@Composable
//fun ContentExpandedPreview() {
//    WeeMealTheme {
//        RecipeListItemContentExpanded(recipe = RecipeMock.generateRecipe())
//    }
//}
