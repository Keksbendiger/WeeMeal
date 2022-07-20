package de.fhe.ai.weemeal.weeklistComponent

import android.graphics.Paint
import android.graphics.drawable.shapes.OvalShape
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
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.common.components.EmptyListText
import de.fhe.ai.weemeal.common.components.TextAndIconButton
import de.fhe.ai.weemeal.common.functions.dayOfWeekString
import de.fhe.ai.weemeal.common.functions.getDaysAhead
import de.fhe.ai.weemeal.common.functions.monthName
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import de.fhe.ai.weemeal.domain.models.Meal
import java.util.Date
import kotlinx.coroutines.ExperimentalCoroutinesApi

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun WeeklistScreen(vm: WeekListViewModel) {
    WeeMealTheme() {
        Scaffold(

            floatingActionButtonPosition = FabPosition.End,

            ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column {

                    val meals: List<Meal> = vm.mealList

                    if (meals.isNotEmpty()) {
                        WeekList(
                            meals = meals,
                            onClickAddToWeekList = { vm.navigateToRecipeList() },
                            onClickNavigateToMeal = { vm.navigateToMealDetail(it) })
                    } else {
                        EmptyListText(text = "Noch keine Wochenliste vorhanden")
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WeekList(
    meals: List<Meal>,
    onClickAddToWeekList: () -> Unit,
    onClickNavigateToMeal: (Long) -> Unit
) {

    var counter = 0
    first@ for (i in 0..100) {
        var day = getDaysAhead(i)
        second@ for (meal in meals) {
            if (meal.cookingDate.day == day.day && meal.cookingDate.month == day.month) {
                counter += 1;
                break@second
            }
        }
    }

    LazyColumn {
        items(counter) { index ->
            var day = getDaysAhead(index)
            WeekListDay(
                meals,
                day,
                onClickAddToWeekList = { onClickAddToWeekList() },
                onClickNavigateToMeal = { onClickNavigateToMeal(it) })
        }
        item {
            TextAndIconButton(
                text = "Neuen Tag hinzufügen",
                icon = Icons.Filled.Add,
                onClick = { addDayToWeekList() })
        }
    }
}

private fun addDayToWeekList() {

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WeekListDay(
    meals: List<Meal>,
    day: Date,
    onClickAddToWeekList: () -> Unit,
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
            if (meal.cookingDate.day == day.day && meal.cookingDate.month == day.month) {
                MealListItem(meal = meal, onClickNavigateToMeal = { onClickNavigateToMeal(it) })
            }
        }
        item { AddMeal(onClickAddToWeekList = { onClickAddToWeekList() }) }
    }
}

@Composable
fun MealListItem(meal: Meal, onClickNavigateToMeal: (Long) -> Unit) {

    Card(
        //onClick = { onClickNavigateToMeal(meal.internalId) },
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(150.dp)
            .width(150.dp)

    ) {

        Image(
            modifier = Modifier.clickable(onClick = { onClickNavigateToMeal(meal.internalId) }),
            painter = painterResource(id = meal.recipe.image),
            contentDescription = "Dummy Image"
        )
        ServingsOfTheMeal(meal = meal)
        MealName(meal = meal)
    }
}

@Composable
fun ServingsOfTheMeal(meal: Meal) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "ADD Meal",
            modifier = Modifier
                .padding(2.dp)
        )
        Text(
            text = meal.servings.toString(),
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .align(Alignment.Top)
        )
    }

}

@Composable
fun MealName(meal: Meal) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
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


@Composable
private fun AddMeal(onClickAddToWeekList: () -> Unit) {
    Button(
        onClick = { onClickAddToWeekList() },
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
        Text(text = "Neuen Tag hinzufügen")
    }
}

@Composable
private fun AddDay() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(50.dp)
    ) {
        Text(
            text = "Neuen Tag hinzufügen",
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Light
            ),
        )
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Day",
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(MaterialTheme.colors.primary)
        )
    }
}
/*
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DefaultPreview() {
    WeeMealTheme {
        WeekList(meals = MealMock.generateWeek())
    }
}*/
