package de.fhe.ai.weemeal.recipeList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.common.components.EmptyListText
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import de.fhe.ai.weemeal.domain.models.Recipe
import kotlinx.coroutines.ExperimentalCoroutinesApi

// @Preview
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun AddRecipeToWeekListScreen(
    vm: AddRecipeToWeekListViewModel
) {
    WeeMealTheme() {
        Scaffold(
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { vm.navigateToAddRecipe() },
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = FloatingActionButtonDefaults.elevation(6.dp)
                ) {
                    Icon(Icons.Filled.Add, "")
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
                            onClickSaveMealToCookingDate = { vm.saveMealToCookingDate(it) },
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
    onClickSaveMealToCookingDate: (Recipe) -> Unit,
) {
    LazyColumn {
        itemsIndexed(
            items = recipes
        ) { index, recipe ->
            RecipeListItem(
                recipe = recipe,
                onClickSaveMealToCookingDate = { onClickSaveMealToCookingDate(it) }
            )
        }
    }
}

@Composable
private fun RecipeListItem(
    recipe: Recipe,
    onClickSaveMealToCookingDate: (Recipe) -> Unit,
) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .clickable(onClick = { onClickSaveMealToCookingDate(recipe) })
            .padding(8.dp)
            .shadow(elevation = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        val imagesize = 90.dp

        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Image(
                painterResource(id = recipe.image),
                contentDescription = "Dummy-Image",
                modifier = Modifier
                    .size(imagesize)
                    .clip(RoundedCornerShape(50.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = recipe.name,
                style = MaterialTheme.typography.h6.copy(),
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
            )
        }
    }
}
