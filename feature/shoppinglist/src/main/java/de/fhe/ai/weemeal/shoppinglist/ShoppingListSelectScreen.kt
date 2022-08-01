package de.fhe.ai.weemeal.shoppinglist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import de.fhe.ai.weemeal.app.ui.screens.core.BottomBar
import de.fhe.ai.weemeal.common.functions.dayOfWeekString
import de.fhe.ai.weemeal.common.functions.getDaysAhead
import de.fhe.ai.weemeal.common.functions.monthName
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.mocks.domain.MealMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.Date

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun ShoppingListSelectScreen(
    vm: ShoppingListSelectScreenViewModel,
    navController: NavController,
) {
    val meals: List<MealOnlyForView>? = vm.mealList
    Scaffold(
        bottomBar = { BottomBar(navController) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { vm.navigateToShoppingList() },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = FloatingActionButtonDefaults.elevation(6.dp)
            ) {
                Icon(Icons.Filled.Done, "")
            }
        }

    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column {


                meals?.let {
                    WeekList(
                        meals,
                        onClickAddToShoppingList = { vm.onClickAddToShoppingList(it) })
                } ?: kotlin.run {
                    Text("Keine Einträge")
                }
            }
        }
    }
}


@Composable
private fun WeekList(
    meals: List<MealOnlyForView>,
    onClickAddToShoppingList: (Long) -> Unit
) {
    LazyColumn {
        items(7) { index ->
            var day = getDaysAhead(index)
            WeekListDay(
                meals,
                day,
                onClickAddToShoppingList = { onClickAddToShoppingList(it) })
        }
    }
}

@Composable
private fun WeekListDay(
    meals: List<MealOnlyForView>,
    day: Date,
    onClickAddToShoppingList: (Long) -> Unit
) {

    for (meal in meals) {
        if (meal.meal.cookingDate.day == day.day && meal.meal.cookingDate.month == day.month && meal.meal.cookingDate.date == day.date) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = dayOfWeekString(day).toString(),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 4.dp)
                )
                Text(
                    text = day.date.toString() + ". " + monthName(day),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 4.dp)
                )
            }
            break
        }
    }

    LazyRow {
        itemsIndexed(items = meals) { index, meal ->
            if (meal.meal.cookingDate.day == day.day && meal.meal.cookingDate.month == day.month && meal.meal.cookingDate.date == day.date) {
                MealListItem(
                    meal = meal,
                    onClickAddToShoppingList = { onClickAddToShoppingList(it) })
            }
        }
    }
}

@Composable
fun MealListItem(
    meal: MealOnlyForView,
    onClickAddToShoppingList: (Long) -> Unit
) {
    if (meal.selected) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .shadow(elevation = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .height(150.dp)
                .width(150.dp)
                .border(
                    width = 2.dp,
                    color = Color(meal.borderColor.color.toColorInt()),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {

            Image(
                modifier = Modifier.clickable(onClick = { onClickAddToShoppingList(meal.meal.internalId) }),
                painter = painterResource(id = meal.meal.recipe.image),
                contentDescription = "Dummy Image"
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ServingsOfTheMeal(meal = meal.meal)
                MealName(meal = meal.meal)
            }
        }
    } else {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .shadow(elevation = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .height(150.dp)
                .width(150.dp)
        ) {
            Image(
                modifier = Modifier.clickable(onClick = { onClickAddToShoppingList(meal.meal.internalId) }),
                painter = painterResource(id = meal.meal.recipe.image),
                contentDescription = "Dummy Image"
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ServingsOfTheMeal(meal = meal.meal)
                MealName(meal = meal.meal)
            }
        }
    }

}

@Composable
fun MealName(meal: Meal) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .height(30.dp)
    ) {

        Text(
            text = meal.recipe.name,
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .height(26.dp)
                .padding(horizontal = 4.dp),
//            modifier = Modifier
//                .align(Alignment.Bottom)
        )
    }
}

@Composable
fun ServingsOfTheMeal(meal: Meal) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "Servings of The Meal",
            modifier = Modifier
                .padding(2.dp)
        )
        Text(
            text = meal.servings.toString(),
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Light
            )
        )
    }
}

