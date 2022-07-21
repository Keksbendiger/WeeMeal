package de.fhe.ai.weemeal.recipeDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.usecases.recipe.GetRecipeById
import de.fhe.ai.weemeal.usecases.recipe.SaveRecipe
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecipeEditViewModel(
    private val recipeId: Long,
    private val navigationManager: NavigationManager
) : ViewModel(), KoinComponent {
    var state = mutableStateOf(RecipeEditState())

    private val saveRecipe: SaveRecipe by inject()
    private val getRecipeById: GetRecipeById by inject()

    init {
        loadRecipe(recipeId)
    }

    private fun loadRecipe(recipeId: Long) {
        viewModelScope.launch {
            state = mutableStateOf(RecipeEditState(getRecipeById.execute(recipeId)!!))
        }
    }

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
        ingredients.forEach {
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
        ingredients.forEach {
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
        ingredients.forEach {
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

    fun saveRecipe() {
        // TODO redirect to former page
        viewModelScope.launch {
            saveRecipe.execute(state.value.convertToRecipe())
        }
    }
}
