package de.fhe.ai.weemeal.repository.recipe

import de.fhe.ai.weemeal.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(): Flow<List<Recipe>>
    suspend fun getRecipe(recipeId: Long): Recipe?
    suspend fun searchRecipeByName(recipeName: String): Flow<List<Recipe>>
    suspend fun insertOrUpdateRecipe(recipe: Recipe): Recipe?
//    suspend fun deleteRecipe(recipe: Recipe): Boolean // Check Return Type
}
