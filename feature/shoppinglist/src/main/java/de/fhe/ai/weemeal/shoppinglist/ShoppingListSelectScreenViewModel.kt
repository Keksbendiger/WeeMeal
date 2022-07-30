package de.fhe.ai.weemeal.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.common.navigation.Screen
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.usecases.meal.SaveMeal
import de.fhe.ai.weemeal.usecases.weekList.GetFutureMeals
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShoppingListSelectScreenViewModel(
    private val navigationManager: NavigationManager
) :
    ViewModel(),
    KoinComponent {
    private val getFutureMeals: GetFutureMeals by inject()

    var mealList by mutableStateOf(emptyList<Meal>())

    init {
        this.getMealsFromDb()
    }

    private fun getMealsFromDb() {
        viewModelScope.launch {
            mealList = getFutureMeals.execute()
        }
    }

    fun navigateToShoppingList(meals: List<Meal>) {
       /* var mealIdList: List<Long> = emptyList()

        for (meal in meals)
        {
            mealIdList.plus(meal.internalId)
        }*/
        navigationManager.navigate(Screen.ShoppingList.navigationCommand(/*mealIdList*/))
    }
}
