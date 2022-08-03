package de.fhe.ai.weemeal.weeklistComponent

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import de.fhe.ai.weemeal.app.ui.screens.core.BottomBar
import de.fhe.ai.weemeal.common.components.TextAndIconButton
import de.fhe.ai.weemeal.common.functions.calcDayDifference
import de.fhe.ai.weemeal.common.functions.dayOfWeekString
import de.fhe.ai.weemeal.common.functions.monthName
import de.fhe.ai.weemeal.domain.models.Meal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun WeeklistScreen(
    vm: WeekListViewModel,
    navController: NavController,
) {
    Scaffold(

        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { BottomBar(navController) },

        ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column {

                val meals: List<WeekDay> = vm.state.value.weekdays

                WeekList(
                    meals = meals,
                    onClickAddToWeekList = { vm.navigateToAddRecipeToWeekList(it) },
                    onClickNavigateToMeal = { vm.navigateToMealDetail(it) },
                    onClickUpdateDaysInWeekList = { vm.addWeekToWeekList() }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WeekList(
    meals: List<WeekDay>,
    onClickAddToWeekList: (Long) -> Unit,
    onClickNavigateToMeal: (Long) -> Unit,
    onClickUpdateDaysInWeekList: () -> Unit
) {
    LazyColumn {
        for (meal in meals) {
            item {
                WeekListDay(
                    meal.meals,
                    meal.day,
                    onClickAddToWeekList = { onClickAddToWeekList(it) },
                    onClickNavigateToMeal = { onClickNavigateToMeal(it) }
                )
            }
        }
        item {
            TextAndIconButton(
                text = "Neuen Woche hinzufügen",
                icon = Icons.Filled.Add,
                onClick = { onClickUpdateDaysInWeekList() }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WeekListDay(
    meals: List<Meal>,
    day: Date,
    onClickAddToWeekList: (Long) -> Unit,
    onClickNavigateToMeal: (Long) -> Unit
) {
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

    LazyRow {
        itemsIndexed(items = meals) { index, meal ->
            if (meal.cookingDate.day == day.day && meal.cookingDate.month == day.month && meal.cookingDate.date == day.date) {
                MealListItem(meal = meal, onClickNavigateToMeal = { onClickNavigateToMeal(it) })
            }
        }
        item { AddMeal(day) { onClickAddToWeekList(it) } }
    }
}

@Composable
fun MealListItem(meal: Meal, onClickNavigateToMeal: (Long) -> Unit) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .shadow(elevation = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .height(150.dp)
            .width(150.dp)
            .border(
                width = 2.dp,
                color = Color(meal.cookColor.color.toColorInt()),
                shape = RoundedCornerShape(8.dp)
            )
    ) {

        Image(
            modifier = Modifier.clickable(onClick = { onClickNavigateToMeal(meal.internalId) }),
            painter = painterResource(id = meal.recipe.image),
            contentDescription = "Dummy Image"
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ServingsOfTheMeal(meal = meal)
            MealName(meal = meal)
        }
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
        )
    }
}

@Composable
private fun AddMeal(
    day: Date,
    onClickAddToWeekList: (Long) -> Unit
) {
    Button(
        onClick = { onClickAddToWeekList(calcDayDifference(day)) },
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(150.dp)
            .width(150.dp)

    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "ADD Meal",
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .background(MaterialTheme.colors.primary)
        )
    }
}
