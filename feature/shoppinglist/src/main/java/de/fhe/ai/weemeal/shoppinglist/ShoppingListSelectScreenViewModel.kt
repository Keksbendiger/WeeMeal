package de.fhe.ai.weemeal.shoppinglist

import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.common.navigation.Screen
import de.fhe.ai.weemeal.domain.enums.CookColor
import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.domain.models.ShoppingList
import de.fhe.ai.weemeal.usecases.weekList.GetFutureMeals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShoppingListSelectScreenViewModel(
    private val navigationManager: NavigationManager
) :
    ViewModel(),
    KoinComponent {
    private val getFutureMeals: GetFutureMeals by inject()


    var shoppingList: ShoppingList = ShoppingList(0, emptyList())
    var state = MutableStateFlow(ShoppingListSelectScreenState(shoppingList))
    var stateListOfMeal =
        MutableStateFlow(MealOnlyForViewWithCounter(emptyList<MealOnlyForView>().toMutableList()))


    init {
        this.getMealsFromDb()
    }

    private fun getMealsFromDb() {
        viewModelScope.launch {
            val meals: List<Meal> = getFutureMeals.execute()
            for (meal in meals) {
                val mealOnlyForView = MealOnlyForView(meal)
                stateListOfMeal.value.mealOnlyForViewList.add(mealOnlyForView)
            }
        }
    }


    fun onClickAddToShoppingList(mealId: Long) {
        val tempList: MutableList<MealOnlyForView> = stateListOfMeal.value.mealOnlyForViewList
        for (meal in tempList) {
            if (meal.meal.internalId == mealId) {
                if (meal.selected == false) {
                    meal.selected = true
                    meal.borderColor = CookColor.RED
                } else {
                    meal.selected = false
                    meal.borderColor = CookColor.TRANSPARENT
                }
            }
        }
        stateListOfMeal.value = stateListOfMeal.value.copy(
            counter = stateListOfMeal.value.counter + 1,
            mealOnlyForViewList = tempList
        )
    }


    fun navigateToShoppingList() {
        var tempIngredientList: List<Ingredient> = emptyList<Ingredient>().toMutableList()
        for (meal in stateListOfMeal.value.mealOnlyForViewList) {
            if (meal.selected) {
                tempIngredientList = state.value.shoppingList.items.toMutableList()
                for (ingredient in meal.meal.recipe.defaultIngredients!!) {
                    tempIngredientList.add(ingredient)
                }
            }
        }
        var newShoppingList = ShoppingList(0, tempIngredientList)
        state.value = state.value.copy(shoppingList = newShoppingList)

        navigationManager.navigate(Screen.ShoppingList.navigationCommand(/*state.value.shoppingList.internalId*/))
    }
}
