package de.darthkali.repository

import de.darthkali.domain.models.recipe.Recipe
import de.darthkali.local.database.recipe.RecipeEntityDao
import de.darthkali.local.mapper.recipe.fromDomain
import de.darthkali.local.mapper.recipe.toDomain
import kotlinx.coroutines.flow.map

class RecipeRepositoryImpl (
    private val recipeEntityDao: RecipeEntityDao
    ): RecipeRepository {

        override fun getUsers() =
            recipeEntityDao.getAllAsFlow()
                .map { entityList ->
                    val returnValue = mutableListOf<Recipe>()

                    entityList.forEach { entity ->
                        returnValue += entity.toDomain()
                    }
                    returnValue
                }

        override suspend fun getUser(recipeId: Long) = recipeEntityDao.get( recipeId )?.toDomain()

        override suspend fun insertUser(recipe: Recipe) = recipeEntityDao.insert(  recipe.fromDomain() )

        override suspend fun updateUser(recipe: Recipe): Long {
            TODO("Not yet implemented")
        }
}