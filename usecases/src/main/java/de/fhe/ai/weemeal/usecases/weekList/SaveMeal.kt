package de.fhe.ai.weemeal.usecases.weekList

import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.repository.meal.MealRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveMeal : KoinComponent {

    private val mealRepository: MealRepository by inject()

    /**
     * TODO
     *
     * Funktionsbeschreibung
     */
    suspend fun execute(meal: Meal) {
        mealRepository.insertOrUpdateMeal(meal)
    }
}
