package de.fhe.ai.weemeal.repository.meal

import de.fhe.ai.weemeal.domain.models.Meal
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    fun getMeals(): Flow<List<Meal>>
    suspend fun getMeal(mealId: Long): Meal?
    suspend fun getAll(): List<Meal>
    suspend fun insertOrUpdateMeal(meal: Meal): Meal?
    suspend fun deleteMeal(meal: Meal): Boolean
    suspend fun deleteAllMeals(): Boolean
}
