package de.fhe.ai.weemeal.repository.ingredient

import de.fhe.ai.weemeal.domain.models.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {
    fun getIngredients(): Flow<List<Ingredient>>
    suspend fun getIngredient(ingredientId: Long): Ingredient?
    suspend fun getAllIngredientsByRecipeId(recipeId: Long): List<Ingredient>?
    suspend fun insertIngredient(ingredient: Ingredient): Long
}
