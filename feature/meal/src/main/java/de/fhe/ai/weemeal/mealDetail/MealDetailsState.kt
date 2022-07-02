package de.fhe.ai.weemeal.mealDetail

import de.fhe.ai.weemeal.domain.enums.CookColor
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.mocks.domain.MealMock
import java.util.Date

data class MealDetailsState(
    val internalId: Long = 0,
    val recipe: Recipe,
    var servings: Int? = 1,
    var cookColor: CookColor? = CookColor.TRANSPARENT,
    val cookingDate: Date,
    val shoppingListCreatedAt: Date? = null,
) {
    constructor(meal: Meal = MealMock.generateSingleObject()) : this(
        internalId = meal.internalId,
        recipe = meal.recipe,
        servings = meal.servings,
        cookColor = meal.cookColor,
        cookingDate = meal.cookingDate,
        shoppingListCreatedAt = meal.shoppingListCreatedAt
    )
}
