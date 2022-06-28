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
import java.time.Instant.now
import java.time.LocalDate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.time.LocalDate.now
import java.time.LocalDateTime.now
import java.time.LocalTime.now
import java.time.MonthDay.now
import java.time.OffsetDateTime.now
import java.time.OffsetTime.now
import java.time.chrono.ThaiBuddhistDate.now
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WeekList(meals: List<Meal>) {

    LazyColumn {
        itemsIndexed(items = meals) { index, meal ->
            var weekListSorted = meals.sortedBy { it.cookingDate }
            val currentDate = LocalDate.now()
            val temp: List<Meal> = emptyList()
            val weekListForDay: List<Meal> = emptyList()

           /*for (meals in weekListSorted) {
                if (currentDate < meals.cookingDate {
                    temp.plus(meals)
                }
            }*/

            /*if(!temp.isEmpty()){
                weekListSorted = temp.sortedBy { it.cookingDate }
            }*/

           for (meals in weekListSorted) {
                if (weekListSorted[0].cookingDate == meals.cookingDate) {
                    weekListForDay.plus(meals)
                    weekListSorted.minus(meals)
                }
            }

            WeekListDay(weekListForDay)
        }
        item { AddDay() }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WeekListDay(meals: List<Meal>) {

    if(!meals.isEmpty()){
        Text(text = meals[0].cookingDate.toString())
    }
    Text(text = LocalDate.now().toString())
    LazyRow {
        itemsIndexed(items = meals) { index, meal ->
            MealListItem(meal = meal)
        }
        item { AddMeal() }
    }
}

@Composable
fun MealListItem(meal: Meal) { // TODO Meals nur an richtigem datum anz4eigen
    Card(
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
// TODO: AddMeal onClick -> create new Meal for the Day
        onClick = {},
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(150.dp)
            .width(150.dp)

    ) {
        Icon(
            imageVector = Icons.Filled.Add,
//                        TODO: Extract String Ressource
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

@Preview
@Composable
fun DefaultPreview() {
    WeeMealTheme {
        WeekList(meals = MealMock.generateList())
    }
}
