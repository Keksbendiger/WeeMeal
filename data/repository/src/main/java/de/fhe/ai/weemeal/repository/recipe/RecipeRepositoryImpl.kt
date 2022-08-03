package de.fhe.ai.weemeal.repository.recipe

import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.domain.models.Tag
import de.fhe.ai.weemeal.local.dao.IngredientEntityDao
import de.fhe.ai.weemeal.local.dao.RecipeEntityDao
import de.fhe.ai.weemeal.local.dao.RecipeIngredientEntityDao
import de.fhe.ai.weemeal.local.dao.RecipeTagEntityDao
import de.fhe.ai.weemeal.local.dao.TagEntityDao
import de.fhe.ai.weemeal.local.entity.RecipeIngredientEntity
import de.fhe.ai.weemeal.local.mapper.fromDomain
import de.fhe.ai.weemeal.local.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class RecipeRepositoryImpl(
    private val recipeEntityDao: RecipeEntityDao,
    private val ingredientEntityDao: IngredientEntityDao,
    private val tagEntityDao: TagEntityDao,
    private val recipeIngredientEntityDao: RecipeIngredientEntityDao,
    private val recipeTagEntityDao: RecipeTagEntityDao,
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

        recipeEntityDao.get(recipeId)?.toDomain()?.let { recipe ->
            val ingredientList: MutableList<Ingredient> = mutableListOf()
            val tagList: MutableList<Tag> = mutableListOf()

            recipeIngredientEntityDao.getAllByRecipeId(recipeId).forEach { recipeIngredient ->
                // println(recipeIngredient.ingredientId)
                // println(ingredientEntityDao.getAll().size)

                ingredientEntityDao.get(recipeIngredient.ingredientId)?.let { ingredientEntity ->
                    // println(ingredientEntity.id)
                    ingredientList.add(
                        ingredientEntity.toDomain()
                    )
                }
            }
            recipe.defaultIngredients = ingredientList

            recipeTagEntityDao.getAllByRecipeId(recipeId).forEach { recipeTagEntity ->
                tagEntityDao.get(recipeTagEntity.id)?.let { tagEntity ->
                    tagList.add(
                        tagEntity.toDomain()
                    )
                }
            }
            recipe.tags = tagList
            return recipe
        }
        return null
    }

    override suspend fun getAll(): List<Recipe> {
        val recipeList = mutableListOf<Recipe>()

        recipeEntityDao.getAll().forEach {
            getRecipe(it.id)?.let { recipe -> recipeList.add(recipe) }
        }

        return recipeList
    }

    override suspend fun searchRecipeByName(recipeName: String): List<Recipe> {
        Timber.i("Search Recipe by Name")
        val returnValue = mutableListOf<Recipe>()
        recipeEntityDao.search(recipeName).forEach { recipeEntity ->
            returnValue.add(getRecipe(recipeEntity.id)!!)
        }
        return returnValue
    }

    /**
     * upsert a Recipe into the database
     * 1. upsert recipeEntity
     * 2. delete all related recipeIngredientEntity to have a clean relation
     * 3. upsert recipeIngredientEntity
     *
     * @param recipe the Recipe to upsert
     * @return the the upserted Recipe
     */
    override suspend fun insertOrUpdateRecipe(recipe: Recipe): Recipe? {

        val recipeId: Long = if (recipeEntityDao.get(recipe.internalId) != null) {
            recipeEntityDao.update(recipe.fromDomain())
            recipe.internalId
        } else {
            recipeEntityDao.insert(recipe.fromDomain())
        }

        recipeIngredientEntityDao.getAllByRecipeId(recipeId).forEach { recipeIngredient ->
            recipeIngredientEntityDao.delete(
                RecipeIngredientEntity(
                    id = recipeIngredient.id,
                    recipeId = recipeIngredient.recipeId,
                    ingredientId = recipeIngredient.ingredientId
                )
            )
        }

        recipe.defaultIngredients?.forEach { ingredient ->

            val ingredientId: Long = if (ingredientEntityDao.get(ingredient.internalId) == null) {
                ingredientEntityDao.insert(ingredient.fromDomain())
            } else {
                ingredientEntityDao.update(ingredient.fromDomain())
                ingredient.internalId
            }

            recipeIngredientEntityDao.insert(
                RecipeIngredientEntity(
                    ingredientId = ingredientId,
                    recipeId = recipeId
                )
            )
        }

        return getRecipe(recipeId)
    }

    override suspend fun deleteRecipe(recipe: Recipe): Boolean {

        recipeIngredientEntityDao.getAllByRecipeId(recipe.internalId)
            .forEach { recipeIngredientEntity ->

                ingredientEntityDao.get(recipeIngredientEntity.ingredientId)
                    ?.let { ingredientEntity ->
                        ingredientEntityDao.delete(ingredientEntity)
                    }

                recipeIngredientEntityDao.delete(recipeIngredientEntity)
            }

        recipeTagEntityDao.getAllByRecipeId(recipe.internalId)
            .forEach { recipeTagEntity ->

                tagEntityDao.get(recipeTagEntity.tagId)?.let { tagEntity ->
                    tagEntityDao.delete(tagEntity)
                }

                recipeTagEntityDao.delete(recipeTagEntity)
            }

        recipeEntityDao.delete(recipe.fromDomain())

        return true // TODO: change
    }

    override suspend fun deleteAllRecipes(): Boolean {

        recipeEntityDao.getAll().forEach {
            recipeIngredientEntityDao.getAllByRecipeId(it.id)
                .forEach { recipeIngredientEntity ->

                    ingredientEntityDao.get(recipeIngredientEntity.ingredientId)
                        ?.let { ingredientEntity ->
                            ingredientEntityDao.delete(ingredientEntity)
                        }

                    recipeIngredientEntityDao.delete(recipeIngredientEntity)
                }

            recipeTagEntityDao.getAllByRecipeId(it.id)
                .forEach { recipeTagEntity ->

                    tagEntityDao.get(recipeTagEntity.tagId)?.let { tagEntity ->
                        tagEntityDao.delete(tagEntity)
                    }

                    recipeTagEntityDao.delete(recipeTagEntity)
                }
        }

        recipeEntityDao.deleteAll()

        return true // TODO: change
    }
}
