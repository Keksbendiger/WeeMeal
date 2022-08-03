package de.fhe.ai.weemeal.mealDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.common.navigation.Screen
import de.fhe.ai.weemeal.domain.enums.CookColor
import de.fhe.ai.weemeal.usecases.meal.GetMealById
import de.fhe.ai.weemeal.usecases.meal.SaveMeal
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MealDetailsViewModel(
    private val mealId: Long,
    private val navigationManager: NavigationManager
) : ViewModel(), KoinComponent {
    private val getMeal: GetMealById by inject()
    private val saveMeal: SaveMeal by inject()

    var state = mutableStateOf(MealDetailsState())

    init {
        viewModelScope.launch {
            state = mutableStateOf(MealDetailsState(getMeal.execute(mealId)!!))
        }
    }

    fun increaseServings() {
        state.value = state.value.copy(servings = state.value.servings?.plus(1))
        saveMeal()
    }

    fun decreaseServings() {
        state.value = state.value.copy(servings = state.value.servings?.minus(1))
        saveMeal()
    }

    fun increaseColor() {
        state.value = state.value.copy(cookColor = CookColor.getNext(state.value.cookColor))
        saveMeal()
    }

    fun decreaseColor() {
        state.value = state.value.copy(cookColor = CookColor.getPrevious(state.value.cookColor))
        saveMeal()
    }

    private fun saveMeal() {
        viewModelScope.launch {
            saveMeal.execute(state.value.convertToMeal())
        }
    }

    fun navigateToRecipeDetails() {
        val recipeId = state.value.recipe.internalId
        if (recipeId != 0L) {
            navigationManager.navigate(Screen.RecipeDetail.navigationCommand(recipeId))
        }
    }
}
