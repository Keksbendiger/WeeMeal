package de.fhe.ai.weemeal.mealDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.common.navigation.Screen
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.usecases.meal.GetMealById
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MealDetailsViewModel(
    private val mealId: Long,
    private val navigationManager: NavigationManager
) : ViewModel(), KoinComponent {
    private val getMeal : GetMealById by inject()

    lateinit var meal: Meal

    init {
        viewModelScope.launch {
            meal = getMeal.execute(mealId)!!
        }
    }

    fun increaseServings() {
        state.value = state.value.copy(servings = state.value.servings?.plus(1))
    }

    fun decreaseServings() {
        state.value = state.value.copy(servings = state.value.servings?.minus(1))
    }

    fun navigateToRecipeDetails(recipeId: Long) {
        navigationManager.navigate(Screen.RecipeDetail.navigationCommand(recipeId))
    }

    var state = mutableStateOf(MealDetailsState())
}
