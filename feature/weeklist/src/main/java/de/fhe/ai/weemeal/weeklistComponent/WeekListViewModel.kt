package de.fhe.ai.weemeal.weeklistComponent

import android.content.Intent.getIntent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.functions.calcDayDifference
import de.fhe.ai.weemeal.common.functions.getDaysAhead
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.common.navigation.Screen
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.usecases.meal.SaveMeal
import de.fhe.ai.weemeal.usecases.usabilityTest.PrepareHCIUsabilityTest
import de.fhe.ai.weemeal.usecases.weekList.GetFutureMeals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeekListViewModel(private val navigationManager: NavigationManager) :
    ViewModel(),
    KoinComponent {

    private val getFutureMeals: GetFutureMeals by inject()

    var state = mutableStateOf(WeekListState(mutableListOf()))

    init {
//        val mockMeals = MealMock.generateWeek()
//        viewModelScope.launch {
//            for (mockMeal in mockMeals) {
//                saveMeal.execute(mockMeal)
//            }
//        }
        // viewModelScope.launch {
        // prepareHCIUsabilityTest.execute()}
        this.getMealsFromDb()
    }

    private fun getMealsFromDb() {
        val weekDays = mutableListOf<WeekDay>()
        viewModelScope.launch {

            val internalMealList = getFutureMeals.execute()


            var day = getDaysAhead(0)
            for (meal in internalMealList) {
                if (meal.cookingDate.date > day.date) {
                    day = meal.cookingDate
                }
            }

            val tempDaysAhead = calcDayDifference(day).toInt()

            if (internalMealList.isEmpty()){
                state.value =
                    state.value.copy(weekdays = state.value.weekdays, amountOfDaysAhead = tempDaysAhead+6)
            }
            else{
                if (tempDaysAhead < 7){
                    state.value =
                        state.value.copy(weekdays = state.value.weekdays, amountOfDaysAhead = 6)
                }
                else{
                    state.value =
                        state.value.copy(weekdays = state.value.weekdays, amountOfDaysAhead = tempDaysAhead)
                }
            }



            for (daysAhead in 0..state.value.amountOfDaysAhead) {
                val internalMealDayList = mutableListOf<Meal>()

                val day = getDaysAhead(daysAhead)

                internalMealList.forEach { meal ->
                    if (meal.cookingDate.day == day.day && meal.cookingDate.month == day.month && meal.cookingDate.date == day.date) {
                        internalMealDayList.add(meal)
                    }
                }
                weekDays.add(WeekDay(day, internalMealDayList))
            }
            state.value = state.value.copy(
                weekdays = weekDays,
                amountOfDaysAhead = state.value.amountOfDaysAhead
            )
        }
    }

    fun navigateToAddRecipeToWeekList(cookingDateDaysAhead: Long) {
        navigationManager.navigate(Screen.AddRecipeToWeekList.navigationCommand(cookingDateDaysAhead))
    }

    fun navigateToMealDetail(mealId: Long) {
        navigationManager.navigate(Screen.MealDetail.navigationCommand(mealId))
    }

    fun addWeekToWeekList() {
        val weekDays = state.value.weekdays
        val internalMealDayList = mutableListOf<Meal>()

        for (daysAhead in state.value.amountOfDaysAhead + 1..state.value.amountOfDaysAhead + 7) {
            val day = getDaysAhead(daysAhead)
            weekDays.add(WeekDay(day, internalMealDayList))
        }

        state.value = state.value.copy(
            weekdays = weekDays,
            amountOfDaysAhead = state.value.amountOfDaysAhead + 6
        )
    }
}
