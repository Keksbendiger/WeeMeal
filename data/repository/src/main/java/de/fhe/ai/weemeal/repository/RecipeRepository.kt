package de.fhe.ai.weemeal.repository

import de.fhe.ai.weemeal.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(): Flow<List<Recipe>>
    suspend fun getRecipe(recipeId: Long): Recipe?
    suspend fun insertRecipe(recipe: Recipe): Long
    suspend fun updateRecipe(recipe: Recipe): Long // Check Return Type
}
