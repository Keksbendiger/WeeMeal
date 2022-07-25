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
import de.fhe.ai.weemeal.local.entity.RecipeTagEntity
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
                ingredientEntityDao.get(recipeIngredient.ingredientId)?.let { ingredientEntity ->
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

    override suspend fun insertOrUpdateRecipe(recipe: Recipe): Recipe? {

        val recipeId: Long = if (recipeEntityDao.get(recipe.internalId) != null) {
            recipeEntityDao.update(recipe.fromDomain())
            recipe.internalId
        } else {
            recipeEntityDao.insert(recipe.fromDomain())
        }

        recipe.tags?.forEach { tag ->
            if (tagEntityDao.get(tag.internalId) != null) {
                tagEntityDao.update(tag.fromDomain())
            } else {
                val tagId = tagEntityDao.insert(tag.fromDomain())
                recipeTagEntityDao.insert(
                    RecipeTagEntity(
                        tagId = tagId,
                        recipeId = recipeId
                    )
                )
            }
        }

        recipe.defaultIngredients?.forEach { ingredient ->
            if (ingredientEntityDao.get(ingredient.internalId) != null) {
                ingredientEntityDao.insert(ingredient.fromDomain())
            } else {
                val ingredientId = ingredientEntityDao.insert(ingredient.fromDomain())
                recipeIngredientEntityDao.insert(
                    RecipeIngredientEntity(
                        ingredientId = ingredientId,
                        recipeId = recipeId
                    )
                )
            }
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
