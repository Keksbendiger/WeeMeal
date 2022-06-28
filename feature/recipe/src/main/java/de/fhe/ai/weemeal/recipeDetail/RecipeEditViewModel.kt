package de.fhe.ai.weemeal.recipeDetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import de.fhe.ai.weemeal.domain.models.Recipe

class RecipeEditViewModel(

) : ViewModel() {
    private var _state = mutableStateOf(RecipeEditState())
    val state: MutableState<RecipeEditState>
        get() = _state


    private fun saveRecipe() {
        // TODO implement save to db
    }

    private fun loadRecipe(id: Int) {
        // TODO implement load from db
    }

    fun OnUpdateNumber (number: Int){
        _state.value = _state.value.copy(defaultServings = number)
    }

    public fun updateIngredientName(id: Long, newValue: String) {
        val ingredients = state.value.defaultIngredients
        ingredients?.forEach {
            if (it.internalId == id) {
                it.name = newValue
                // TODO break loop here
            }
        }
        state.value = state.value.copy(defaultIngredients = ingredients)
    }

    public fun deleteIngredient(id: Long) {
        val ingredients = state.value.defaultIngredients
        ingredients?.forEach {
            if (it.internalId == id) {
                ingredients.remove(it)
            }
        }
    }
}