package de.fhe.ai.weemeal.recipeList

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.R
import de.fhe.ai.weemeal.common.components.CustomChip
import de.fhe.ai.weemeal.common.components.EmptyListText
import de.fhe.ai.weemeal.common.components.ListComponent
import de.fhe.ai.weemeal.common.components.TextAndIconButton
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import de.fhe.ai.weemeal.domain.models.Recipe
import kotlinx.coroutines.ExperimentalCoroutinesApi

// @Preview
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RecipeListScreen(
    vm: RecipeListViewModel
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
                    onClick = { vm.navigateToAddRecipe() },
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = FloatingActionButtonDefaults.elevation(6.dp)
                ) {
                    Icon(Filled.Add, "")
                }
            }

        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column {
//                    SearchAppBar(
//                        query = "", // recipeListState.query,
//                        onQueryChanged = {
// //                            onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
//                        },
//                        onExecuteSearch = {
// //                            onTriggerEvent(RecipeListEvents.NewSearch)
//                        },
//                    )
                    var recipes: List<Recipe> = vm.recipeList

                    if (recipes.isNotEmpty()) {
                        RecipeList(
                            recipes = recipes,
                            onClickAddToWeekList = { vm.navigateToAddToWeekList() },
                            onClickRecipeDetail = { vm.navigateToRecipeDetail(it) },
                            onClickRecipeEdit = { vm.navigateToRecipeEdit(it) }
                        )
                    } else {
                        EmptyListText(text = "Noch keine Rezepte vorhanden...")
                    }
                }
            }
        }
    }
}

@Composable
private fun RecipeList(
    recipes: List<Recipe>,
    onClickAddToWeekList: () -> Unit,
    onClickRecipeDetail: (Long) -> Unit,
    onClickRecipeEdit: (Long) -> Unit
) {
//    TODO: Remember where list was left and move back to that index
//    val listState = rememberLazyListState()

    LazyColumn {
        itemsIndexed(
            items = recipes
        ) { index, recipe ->
            RecipeListItem(
                recipe = recipe,
                onClickAddToWeekList = { onClickAddToWeekList() },
                onClickRecipeDetail = { onClickRecipeDetail(it) },
                onClickRecipeEdit = { onClickRecipeEdit(it) }
            )
        }
    }
}

@Composable
private fun RecipeListItem(
    recipe: Recipe,
    onClickAddToWeekList: () -> Unit,
    onClickRecipeDetail: (Long) -> Unit,
    onClickRecipeEdit: (Long) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(8.dp)
            .shadow(elevation = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        RecipeListItemContent(
            recipe = recipe,
            onClickAddToWeekList = { onClickAddToWeekList() },
            onClickRecipeDetail = { onClickRecipeDetail(it) },
            onClickRecipeEdit = { onClickRecipeEdit(it) }
        )
    }
}

@Composable
private fun RecipeListItemContent(
    recipe: Recipe,
    onClickAddToWeekList: () -> Unit,
    onClickRecipeDetail: (Long) -> Unit,
    onClickRecipeEdit: (Long) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val imagesize = 90.dp // also used for Column height of recipe name + expand icon
    val expandIconSize = 24.dp

    Column(
        modifier = Modifier
//            .fillMaxWidth()
            .clickable(onClick = { expanded = !expanded })
            .padding(10.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Box() // Align ExpandIcon to BottomCenter in Row
        {
            Row() {
                Image(
                    painterResource(id = recipe.image),
                    contentDescription = "Dummy-Image",
                    modifier = Modifier
                        .size(imagesize)
                        .clip(RoundedCornerShape(50.dp))
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(imagesize),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = recipe.name,
                        style = MaterialTheme.typography.h6.copy()
                    )

                    // Buffer to make recipename not touch the expandicon
                    Spacer(modifier = Modifier.height(expandIconSize))
                }

                IconButton(
                    onClick = { onClickRecipeEdit(recipe.internalId) },
                    modifier = Modifier
                        .align(Alignment.Top)
                ) {
                    Icon(
                        imageVector = Filled.Edit,
                        contentDescription = stringResource(
                            de.fhe.ai.weemeal.recipe.R.string.edit_recipe
                        )
                    )
                }
            }

            if (!expanded) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_expand_more),
                    contentDescription = stringResource(R.string.show_more),
                    modifier = Modifier
                        .size(expandIconSize)
                        .align(Alignment.BottomCenter)
                )
            }
        }

        if (expanded) {
            RecipeListItemContentExpanded(
                recipe = recipe,
                onClickAddToWeekList = { onClickAddToWeekList() },
                onClickRecipeDetail = { onClickRecipeDetail(it) }
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_expand_less),
                contentDescription = stringResource(R.string.show_less),
                modifier = Modifier
                    .size(expandIconSize)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun RecipeListItemContentExpanded(
    recipe: Recipe,
    onClickAddToWeekList: () -> Unit,
    onClickRecipeDetail: (Long) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
//        Text(text = "Some Recipe Info:")

        Spacer(modifier = Modifier.height(10.dp))

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

        TextAndIconButton(
            text = "Zu Wochenplan hinzufügen",
            icon = Icons.Filled.Add,
            onClick = { onClickAddToWeekList() }
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextAndIconButton(
            "Rezept ansehen",
            Icons.Filled.Search,
            onClick = { onClickRecipeDetail(recipe.internalId) }
        )
    }
}

// @Preview
// @Composable
// fun DefaultPreview() {
//    WeeMealTheme {
//        RecipeList(recipes = RecipeMock.generateList())
//    }
// }
//
// @Preview
// @Composable
// fun ContentExpandedPreview() {
//    WeeMealTheme {
//        RecipeListItemContentExpanded(recipe = RecipeMock.generateSingleObject())
//    }
// }
