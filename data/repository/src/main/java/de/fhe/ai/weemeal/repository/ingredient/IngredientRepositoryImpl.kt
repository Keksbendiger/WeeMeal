package de.fhe.ai.weemeal.repository.ingredient

import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.local.dao.IngredientEntityDao
import de.fhe.ai.weemeal.local.mapper.fromDomain
import de.fhe.ai.weemeal.local.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class IngredientRepositoryImpl(
    private val ingredientEntityDao: IngredientEntityDao,
//    private val recipeIngredientEntityDao: RecipeIngredientEntityDao,

) : IngredientRepository {

    override fun getIngredients(): Flow<List<Ingredient>> {
        Timber.i("Get All Ingredients from database as Flow")

        return ingredientEntityDao.getAllAsFlow()
            .map { entityList ->
                val returnValue = mutableListOf<Ingredient>()

                entityList.forEach { entity ->
                    returnValue += entity.toDomain()
                }
                returnValue
            }
    }

    override suspend fun getIngredient(ingredientId: Long): Ingredient? {
        Timber.i("Get Ingredient from database by ID")
        return ingredientEntityDao.get(id = ingredientId)?.toDomain()
    }

    override suspend fun getAllIngredientsByRecipeId(recipeId: Long): List<Ingredient>? {

//        Timber.i("Get all Ingredients by recipe ID")
//        val ingredientResultList: MutableList<Ingredient> = mutableListOf()
//
//        recipeIngredientEntityDao.getAllByRecipeId(recipeId)
//            .let { recipeIngredientEntities ->
//                recipeIngredientEntities.forEach { recipeIngredientEntity ->
//                    ingredientEntityDao.get(recipeIngredientEntity.ingredientId)?.toDomain()
//                        ?.let { ingredient -> ingredientResultList.add(ingredient) }
//                }
//            }
//
//        return ingredientResultList

        return null
    }

    override suspend fun insertIngredient(ingredient: Ingredient) =
        ingredientEntityDao.insert(ingredient.fromDomain())

//    override suspend fun updateIngredient(ingredient: Ingredient) {
//        ingredientEntityDao.update(ingredient.fromDomain())
//    }

//    override suspend fun getIngredient(ingredientId: Long): Ingredient? {
//        try {
//            ingredientEntityDao.get(ingredientId)?.toDomain()?.let { ingredient ->
//                val ingredientResultList: MutableList<Ingredient> = mutableListOf()
//
//                ingredientIngredientEntityDao.getAllByIngredientId(ingredientId)
//                    .let { ingredientIngredientEntities ->
//                        ingredientIngredientEntities.forEach { ingredientIngredientEntity ->
//                            ingredientEntityDao.get(ingredientIngredientEntity.ingredientId)?.toDomain()
//                                ?.let { ingredient -> ingredientResultList.add(ingredient) }
//                        }
//                    }
//                ingredient.defaultIngredients = ingredientResultList
//                return ingredient
//            }
//            return null
//        } catch (e: Exception) {
// //            logger.log(e.toString())
//            return null
//        }
//    }
}
