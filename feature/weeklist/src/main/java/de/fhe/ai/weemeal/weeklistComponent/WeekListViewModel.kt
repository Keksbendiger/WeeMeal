package de.fhe.ai.weemeal.weeklistComponent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        viewModelScope.launch {
            mealList = getFutureMeals.execute()
        }
    }

    fun navigateToAddRecipeToWeekList(cookingDate: String) {
        navigationManager.navigate(Screen.AddRecipeToWeekList.navigationCommand(cookingDate))
    }

    fun navigateToMealDetail(mealId: Long) {
        navigationManager.navigate(Screen.MealDetail.navigationCommand(mealId))
    }

    fun addDayToWeekList(number: Int) {
        val temp = number + 1
        state.value = state.value.copy(counter = temp)
    }
}
