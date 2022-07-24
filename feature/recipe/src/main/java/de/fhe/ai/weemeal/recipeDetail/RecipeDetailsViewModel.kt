package de.fhe.ai.weemeal.recipeDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.common.navigation.Screen
import de.fhe.ai.weemeal.usecases.recipe.GetRecipeById
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecipeDetailsViewModel(
    private val recipeId: Long,
    private val navigationManager: NavigationManager
) : ViewModel(), KoinComponent {
    private val getRecipeById: GetRecipeById by inject()

    var state = mutableStateOf(RecipeEditState())

    init {
        loadRecipe(recipeId)
    }

    private fun loadRecipe(recipeId: Long) {
        viewModelScope.launch {
            state = mutableStateOf(RecipeEditState(getRecipeById.execute(recipeId)!!))
        }
    }

    fun navigateToEditRecipe() {
        navigationManager.navigate(Screen.RecipeEdit.navigationCommand(recipeId))
    }
}
