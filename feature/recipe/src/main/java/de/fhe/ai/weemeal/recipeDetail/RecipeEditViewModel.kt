package de.fhe.ai.weemeal.recipeDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.domain.models.Ingredient

class RecipeEditViewModel : ViewModel() {
    var state = mutableStateOf(RecipeEditState())

//    private fun saveRecipe() {
//        // TODO implement save to db
//    }
//
//    private fun loadRecipe(id: Int) {
//        // TODO implement load from db
//    }

    fun OnUpdateDefaultServings(number: Int) {
        state.value = state.value.copy(defaultServings = number)
    }

    fun onUpdateInstructions(instructions: String) {
        state.value = state.value.copy(instructions = instructions)
    }

    fun onUpdatePreparationTime(value: Float, unit: String) {
        state.value = state.value.copy(timePreparation = TimeFormat(value, unit))
    }

    fun onUpdateActiveCookingTime(value: Float, unit: String) {
        state.value = state.value.copy(timeActiveCooking = TimeFormat(value, unit))
    }

    fun onUpdateOverallCookingTime(value: Float, unit: String) {
        state.value = state.value.copy(timeOverall = TimeFormat(value, unit))
    }

    fun updateIngredientName(id: Long, newValue: String) {
        val ingredients: MutableList<Ingredient> = state.value.defaultIngredients!!
        ingredients?.forEach {
            if (it.internalId == id) {
                it.name = newValue
                // TODO break loop here
            }
        }
        state.value =
            state.value.copy(defaultIngredients = ingredients, counter = state.value.counter + 1)
    }

    fun updateIngredientAmount(id: Long, newValue: Float) {
        val ingredients: MutableList<Ingredient> = state.value.defaultIngredients!!
        ingredients?.forEach {
            if (it.internalId == id) {
                it.quantity.quantity = newValue
                // TODO break loop here
            }
        }
        state.value =
            state.value.copy(defaultIngredients = ingredients, counter = state.value.counter + 1)
    }

    fun updateIngredientUnit(id: Long, newValue: String) {
        val ingredients: MutableList<Ingredient> = state.value.defaultIngredients!!
        ingredients?.forEach {
            if (it.internalId == id) {
                it.quantity.unit = newValue
                // TODO break loop here
            }
        }
        state.value =
            state.value.copy(defaultIngredients = ingredients, counter = state.value.counter + 1)
    }

    fun deleteIngredient(id: Long) {
        val ingredients = state.value.defaultIngredients
        ingredients?.forEach {
            if (it.internalId == id) {
                ingredients.remove(it)
            }
        }
        state.value =
            state.value.copy(defaultIngredients = ingredients, counter = state.value.counter + 1)
    }
}
