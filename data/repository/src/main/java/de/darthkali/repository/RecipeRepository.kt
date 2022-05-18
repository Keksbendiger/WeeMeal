package de.darthkali.repository

import de.darthkali.domain.models.recipe.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getUsers(): Flow<List<Recipe>>
    suspend fun getUser( userId: Long ) : Recipe?
    suspend fun insertUser( user: Recipe ): Long
    suspend fun updateUser( user: Recipe ): Long // Check Return Type
}