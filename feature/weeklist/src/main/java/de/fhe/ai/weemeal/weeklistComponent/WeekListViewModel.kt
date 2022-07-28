package de.fhe.ai.weemeal.weeklistComponent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.functions.getDaysAhead
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.common.navigation.Screen
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.usecases.meal.SaveMeal
import de.fhe.ai.weemeal.usecases.usabilityTest.PrepareHCIUsabilityTest
import de.fhe.ai.weemeal.usecases.weekList.GetFutureMeals
import java.util.Date
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeekListViewModel(private val navigationManager: NavigationManager) :
    ViewModel(),
    KoinComponent {

    private val saveMeal: SaveMeal by inject()
    private val getFutureMeals: GetFutureMeals by inject()
    private val prepareHCIUsabilityTest: PrepareHCIUsabilityTest by inject()

    var state = MutableStateFlow(WeekListState())
    var mealList by mutableStateOf(emptyList<Meal>())

    init {
        // viewModelScope.launch {
        // prepareHCIUsabilityTest.execute()}
        this.getMealsFromDb()
    }

    private fun getMealsFromDb() {
        val weekDays = mutableListOf<WeekDay>()
        viewModelScope.launch {

            val internalMealList = getFutureMeals.execute()

            /*first@ for (i in 0..100) {
                var day = getDaysAhead(i)
                second@ for (meal in internalMealList) {
                    if (meal.cookingDate.day == day.day && meal.cookingDate.month == day.month) {
                        var tempCounter = state.value.amountOfDaysAhead
                        tempCounter += 1
                        state.value = state.value.copy(amountOfDaysAhead = tempCounter)
                        break@second
                    }
                }
            }*/

            for (daysAhead in 0..14/*state.value.amountOfDaysAhead*/) {
                val internalMealDayList = mutableListOf<Meal>()

                val day = getDaysAhead(daysAhead)

                internalMealList.forEach { meal ->
                    if (meal.cookingDate.day == day.day && meal.cookingDate.month == day.month) {
                        internalMealDayList.add(meal)
                    }
                }
                weekDays.add(WeekDay(day, internalMealDayList))
            }
            mealList = getFutureMeals.execute()
        }
        state.value.weekdays = weekDays
    }

    fun navigateToAddRecipeToWeekList(cookingDateDaysAhead: Int) {
        navigationManager.navigate(Screen.AddRecipeToWeekList.navigationCommand(cookingDateDaysAhead))
    }

    fun navigateToMealDetail(mealId: Long) {
        navigationManager.navigate(Screen.MealDetail.navigationCommand(mealId))
    }

    fun addDayToWeekList() {
        var temp = state.value.amountOfDaysAhead
        temp += 1
        var temp2 = state.value.weekdays
        state.value = state.value.copy(amountOfDaysAhead = temp, weekdays = temp2)
        navigationManager.navigate(Screen.WeekList.navigationCommand())
    }
}
