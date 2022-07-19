package de.fhe.ai.weemeal.usecases.weekList

import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.repository.meal.MealRepository
import org.koin.core.component.inject
import org.koin.core.component.KoinComponent

class GetFutureMeals : KoinComponent {
    private val mealRepository: MealRepository by inject()

    /**
     * TODO
     *
     * Funktionsbeschreibung
     */
    suspend fun execute(): List<Meal>{
        return mealRepository.getAllMealsWhereDateIsTodayOrLater()
    }
}