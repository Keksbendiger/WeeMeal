package de.fhe.ai.weemeal.repository.meal

import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.local.dao.MealEntityDao
import de.fhe.ai.weemeal.local.mapper.fromDomain
import de.fhe.ai.weemeal.local.mapper.toDomain
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

class MealRepositoryImpl(
    private val mealEntityDao: MealEntityDao,
) : MealRepository, KoinComponent {

    private val recipeRepository: RecipeRepository by inject()

    override fun getMeals(): Flow<List<Meal>> {
        Timber.i("Get All Meals from database as Flow")

        return mealEntityDao.getAllAsFlow()
            .map { entityList ->
                val returnValue = mutableListOf<Meal>()

                entityList.forEach { entity ->
                    returnValue += entity.toDomain()
                }
                returnValue
            }
    }

    override suspend fun getMeal(mealId: Long): Meal? {
        Timber.i("Get Meal from database by ID")

        mealEntityDao.get(mealId)?.let { mealEntity ->
            val meal = mealEntity.toDomain()
            meal.recipe = recipeRepository.getRecipe(mealEntity.recipeId)!!
            return meal
        }
        return null
    }

    override suspend fun getAll(): List<Meal> {
        val mealList = mutableListOf<Meal>()

        mealEntityDao.getAll().forEach {
            getMeal(it.id)?.let { meal -> mealList.add(meal) }
        }

        return mealList
    }

    override suspend fun insertOrUpdateMeal(meal: Meal): Meal? {

        val mealId: Long = if (mealEntityDao.get(meal.internalId) != null) {
            meal.recipe = recipeRepository.insertOrUpdateRecipe(meal.recipe)!!
            mealEntityDao.update(meal.fromDomain())
            meal.internalId
        } else {
            meal.recipe = recipeRepository.insertOrUpdateRecipe(meal.recipe)!!
            mealEntityDao.insert(meal.fromDomain())
        }

        return getMeal(mealId)
    }

    override suspend fun deleteMeal(meal: Meal) {
        mealEntityDao.delete(meal.fromDomain())
    }

    override suspend fun deleteAllMeals() {
        mealEntityDao.deleteAll()
    }
}
