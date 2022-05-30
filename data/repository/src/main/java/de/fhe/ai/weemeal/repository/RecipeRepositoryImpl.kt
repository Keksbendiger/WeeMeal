package de.fhe.ai.weemeal.repository

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.local.dao.RecipeEntityDao
import de.fhe.ai.weemeal.local.mapper.recipe.fromDomain
import de.fhe.ai.weemeal.local.mapper.recipe.toDomain
import kotlinx.coroutines.flow.map

class RecipeRepositoryImpl(
    private val recipeEntityDao: RecipeEntityDao
) : de.fhe.ai.weemeal.repository.RecipeRepository {

    override fun getRecipes() =
        recipeEntityDao.getAllAsFlow()
            .map { entityList ->
                val returnValue = mutableListOf<Recipe>()

                entityList.forEach { entity ->
                    returnValue += entity.toDomain()
                }
                returnValue
            }

    override suspend fun getRecipe(recipeId: Long) = recipeEntityDao.get(recipeId)?.toDomain()

    override suspend fun insertRecipe(recipe: Recipe) = recipeEntityDao.insert(recipe.fromDomain())

    override suspend fun updateRecipe(recipe: Recipe): Long {
        TODO("Not yet implemented")
    }
}
