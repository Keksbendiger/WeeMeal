package de.fhe.ai.weemeal.weeklistComponent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.common.components.SearchAppBar
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.mocks.domain.MealMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun WeeklistScreen() {
    WeeMealTheme() {
        Scaffold(
            floatingActionButtonPosition = FabPosition.End,
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
                    val meals: List<Meal>? = MealMock.generateWeek()

//                  Nullcheck -> TODO: More elegant way possible?
                    meals?.let {
                        WeekList(meals)
                    } ?: kotlin.run {
                        Text("Keine Einträge")
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WeekList(meals: List<Meal>) {

    meals.sortedByDescending { it.cookingDate }
    LazyColumn {
        items(14) { index ->
            var day = getDaysAhead(index)
            WeekListDay(meals, day)
        }
        item { AddDay() }
    }
}

fun getDaysAhead(daysAhead: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, daysAhead)

    return calendar.time
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WeekListDay(meals: List<Meal>, day: Date) {
    Text(text = dayOfWeekString(day).toString(), style = MaterialTheme.typography.h6)
    Text(text = day.date.toString() + "." + monthName(day), style = MaterialTheme.typography.h6)

    LazyRow {
        itemsIndexed(items = meals) { index, meal ->
            if (meal.cookingDate.day == day.day && meal.cookingDate.month == day.month) {
                MealListItem(meal = meal)
            }
        }
        item { AddMeal() }
    }
}

fun monthName(day: Date): Any? {
    var month = day.month
    var monthName = ""

    if (month == 1) {
        monthName = "Januar"
    }
    if (month == 2) {
        monthName = "Februar"
    }
    if (month == 3) {
        monthName = "März"
    }
    if (month == 4) {
        monthName = "April"
    }
    if (month == 5) {
        monthName = "Mai"
    }
    if (month == 6) {
        monthName = "Juni"
    }
    if (month == 7) {
        monthName = "Juli"
    }
    if (month == 8) {
        monthName = "August"
    }
    if (month == 9) {
        monthName = "September"
    }
    if (month == 10) {
        monthName = "Oktober"
    }
    if (month == 11) {
        monthName = "November"
    }
    if (month == 12) {
        monthName = "Dezemeber"
    }

    return monthName
}

fun dayOfWeekString(day: Date): Any {
    var dayOfWeek = day.day
    var dayOfWeekString = ""

    if (dayOfWeek == 0) {
        dayOfWeekString = "Sonntag"
    }
    if (dayOfWeek == 1) {
        dayOfWeekString = "Montag"
    }
    if (dayOfWeek == 2) {
        dayOfWeekString = "Dienstag"
    }
    if (dayOfWeek == 3) {
        dayOfWeekString = "Mittwoch"
    }
    if (dayOfWeek == 4) {
        dayOfWeekString = "Donnerstag"
    }
    if (dayOfWeek == 5) {
        dayOfWeekString = "Freitag"
    }
    if (dayOfWeek == 6) {
        dayOfWeekString = "Samstag"
    }

    return dayOfWeekString
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MealListItem(meal: Meal) {
    Card(
        onClick = { /*TODO: Redirect to the Receipt*/ },
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(150.dp)
            .width(150.dp)
    ) {
        Image(painter = painterResource(id = meal.recipe.image), contentDescription = "Dummy Image")
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

@Composable
private fun AddMeal() {
    Button(
        onClick = { /*TODO: AddMeal onClick -> create new Meal for the Day*/ },
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
            .height(40.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Create,
            contentDescription = "Add Day",
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(MaterialTheme.colors.primary)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DefaultPreview() {
    WeeMealTheme {
        WeekList(meals = MealMock.generateWeek())
    }
}
