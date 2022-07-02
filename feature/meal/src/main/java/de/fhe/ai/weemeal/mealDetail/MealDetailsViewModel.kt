package de.fhe.ai.weemeal.mealDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MealDetailsViewModel : ViewModel() {
    fun increaseServings() {
        state.value = state.value.copy(servings = state.value.servings?.plus(1))
    }

    fun decreaseServings() {
        state.value = state.value.copy(servings = state.value.servings?.minus(1))
    }

    var state = mutableStateOf(MealDetailsState())
}
