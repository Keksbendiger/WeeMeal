package de.fhe.ai.weemeal.usecases.recipe

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.repository.meal.MealRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetRecipeByMeal : KoinComponent {

    private val mealRepository: MealRepository by inject()

    suspend fun execute(
        mealId: Long,
    ): Recipe? {
        return mealRepository.getMeal(mealId)?.recipe
    }
}
