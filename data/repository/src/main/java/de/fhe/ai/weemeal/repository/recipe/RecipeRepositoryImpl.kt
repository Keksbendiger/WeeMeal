package de.fhe.ai.weemeal.repository.recipe

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.local.dao.RecipeEntityDao
import de.fhe.ai.weemeal.local.mapper.recipe.fromDomain
import de.fhe.ai.weemeal.local.mapper.recipe.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class RecipeRepositoryImpl(
    private val recipeEntityDao: RecipeEntityDao,
//    private val ingredientRepository: IngredientRepository,
) : RecipeRepository {

    override fun getRecipes(): Flow<List<Recipe>> {
        Timber.i("Get All Recipes from database as Flow")

        return recipeEntityDao.getAllAsFlow()
            .map { entityList ->
                val returnValue = mutableListOf<Recipe>()

                entityList.forEach { entity ->
                    returnValue += entity.toDomain()
                }
                returnValue
            }
    }

    override suspend fun getRecipe(recipeId: Long): Recipe? {

        Timber.i("Get Recipe from database by ID")
//        recipeEntityDao.get(recipeId)?.toDomain()?.let { recipe ->
//            recipe.defaultIngredients =
//                ingredientRepository.getAllIngredientsByRecipeId(recipeId)
//
//        }
        return null // TODO replace with a real return
    }

    override suspend fun insertRecipe(recipe: Recipe) =
        recipeEntityDao.insert(recipe.fromDomain())

//    override suspend fun updateRecipe(recipe: Recipe) {
//        recipeEntityDao.update(recipe.fromDomain())
//    }
}
