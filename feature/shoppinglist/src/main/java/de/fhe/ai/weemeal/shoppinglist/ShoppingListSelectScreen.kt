package de.fhe.ai.weemeal.shoppinglist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.common.components.SearchAppBar
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.mocks.domain.MealMock
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun ShoppingListSelectScreen() {
    WeeMealTheme() {
        Scaffold(

            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { },
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = FloatingActionButtonDefaults.elevation(6.dp)
                ) {
                    Icon(Icons.Filled.Done, "")
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
                    val meals: List<Meal>? = MealMock.generateList()

//                  Nullcheck -> TODO: More elegant way possible?
                    meals?.let {
                        WeekList(meals)
                    } ?: kotlin.run {
//                        TODO: String ressource location correct?
                        Text("Keine Einträge")
                    }
                }
            }
        }
    }
}

@Composable
private fun WeekList(meals: List<Meal>) {
    LazyColumn {
        itemsIndexed(items = meals) { index, meal ->
            WeekListDay(meals = meals)
        }
    }
}

@Composable
private fun WeekListDay(meals: List<Meal>) {
    Text(text = meals[0].cookingDate.toString())
    LazyRow {
        itemsIndexed(items = meals) { index, meal ->
            MealListItem(meal = meal)
        }
    }
}

@Composable
fun MealListItem(meal: Meal) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(150.dp)
            .width(150.dp)
    ) {
        WeekListContent(meal = meal)
    }
}

@Composable
fun WeekListContent(meal: Meal) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            // Redirect to receiptView
            .clickable(onClick = { })
            .padding(8.dp)

    ) {
        Text(
            text = meal.recipe.name,
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .align(Alignment.Bottom)

        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    WeeMealTheme {
        WeekList(meals = MealMock.generateList())
    }
}
