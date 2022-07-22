package de.fhe.ai.weemeal.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.common.navigation.Screen
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.mocks.domain.MealMock
import de.fhe.ai.weemeal.usecases.weekList.GetFutureMeals
import de.fhe.ai.weemeal.usecases.weekList.SaveMeal
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class ShoppingListSelectScreenViewModel(private val navigationManager: NavigationManager) : ViewModel(),
    KoinComponent {

    private val saveMeal: SaveMeal by inject()
    private val getFutureMeals: GetFutureMeals by inject()

    var mealList by mutableStateOf(emptyList<Meal>())

    init {
        val mockMeals = MealMock.generateWeek()
        viewModelScope.launch {
            for (mockMeal in mockMeals) {
                saveMeal.execute(mockMeal)
            }
        }
        this.getMealsFromDb()
    }

    private fun getMealsFromDb() {
        viewModelScope.launch {
            mealList = getFutureMeals.execute()
        }
    }


    fun navigateToRecipeList() {
        navigationManager.navigate(Screen.RecipeList.navigationCommand())
    }

    fun navigateToMealDetail(mealId: Long) {
        navigationManager.navigate(Screen.MealDetail.navigationCommand(mealId))
    }

}