package de.fhe.ai.weemeal.recipeDetail

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import de.fhe.ai.weemeal.domain.models.Recipe

class RecipeEditViewModel : ViewModel(){
    val state: MutableState<RecipeEditState>
    get() = state


    private fun saveRecipe() {
        // TODO implement save to db
    }

    private fun loadRecipe(id: Int) {
        // TODO implement load from db
    }

    private fun onUpdateRecipe(recipe: Recipe) {
        state.value = state.value.copy(recipe = recipe)
    }
}