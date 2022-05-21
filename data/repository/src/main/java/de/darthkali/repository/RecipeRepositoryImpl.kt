package de.darthkali.repository

import de.darthkali.domain.models.Recipe
import de.darthkali.local.mapper.recipe.fromDomain
import de.darthkali.local.mapper.recipe.toDomain
import de.darthkali.local.recipe.RecipeEntityDao
import kotlinx.coroutines.flow.map

class RecipeRepositoryImpl(
    private val recipeEntityDao: RecipeEntityDao
) : RecipeRepository {

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
