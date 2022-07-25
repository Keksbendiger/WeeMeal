package de.fhe.ai.weemeal.recipeDetail

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.navigation.GoBackDestination
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.usecases.recipe.GetRecipeById
import de.fhe.ai.weemeal.usecases.recipe.SaveRecipe
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecipeEditViewModel(
    recipeId: Long,
    private val navigationManager: NavigationManager
) : ViewModel(), KoinComponent {
    var state = mutableStateOf(RecipeEditState())

    private val saveRecipe: SaveRecipe by inject()
    private val getRecipeById: GetRecipeById by inject()

    init {
        if (recipeId != 0L) {
            viewModelScope.launch {
                val recipe = getRecipeById.execute(recipeId)!!
                state.value = state.value.copy(
                    internalId = recipe.internalId,
                    name = recipe.name,
                    defaultServings = recipe.defaultServings,
                    defaultIngredients = recipe.defaultIngredients?.toMutableList(),
                    timePreparation = recipe.timePreparation,
                    timeActiveCooking = recipe.timeActiveCooking,
                    timeOverall = recipe.timeOverall,
                    instructions = recipe.instructions,
                    image = recipe.image,
                    tags = recipe.tags?.toMutableList()
                )
            }
        }
    }

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
        val ingredientsIterator = ingredients?.iterator()
        while (ingredientsIterator!!.hasNext()) {
            val ingredient = ingredientsIterator.next()
            if (ingredient.internalId == id) {
                ingredientsIterator.remove()
            }
        }

        state.value =
            state.value.copy(defaultIngredients = ingredients, counter = state.value.counter + 1)
    }

    fun saveRecipe(context: Context) {
        if (checkIngredientsForEmptyName(context)) {
            viewModelScope.launch {
                saveRecipe.execute(state.value.convertToRecipe())
            }
            navigationManager.navigate(GoBackDestination)
        }
    }

    fun onAddIngredient(context: Context) {
        if (checkIngredientsForEmptyName(context)) {
            val ingredients: MutableList<Ingredient> = state.value.defaultIngredients!!
            ingredients.add(Ingredient(name = ""))

            state.value = state.value.copy(defaultIngredients = ingredients, counter = state.value.counter + 1)
        }
    }

    // @returns TRUE if NO EMPTY NAME   and     FALSE if an empty name WAS FOUND
    private fun checkIngredientsForEmptyName(context: Context): Boolean {
        val ingredients: MutableList<Ingredient> = state.value.defaultIngredients!!
        ingredients.forEach {
            if (it.name.isBlank()) {
                val toast = Toast.makeText(context, "Zutat ohne Namen", Toast.LENGTH_LONG)
                toast.show()
                return false
            }
        }
        return true
    }
}
