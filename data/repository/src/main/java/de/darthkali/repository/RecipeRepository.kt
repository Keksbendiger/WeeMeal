package de.darthkali.repository

import de.darthkali.domain.models.recipe.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(): Flow<List<Recipe>>
    suspend fun getRecipe( recipeId: Long ) : Recipe?
    suspend fun insertRecipe( recipe: Recipe ): Long
    suspend fun updateRecipe( recipe: Recipe ): Long // Check Return Type
}