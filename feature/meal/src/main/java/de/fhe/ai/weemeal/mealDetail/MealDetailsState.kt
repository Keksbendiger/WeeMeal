package de.fhe.ai.weemeal.mealDetail

import de.fhe.ai.weemeal.domain.enums.CookColor
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.domain.models.Recipe
import java.util.Date

data class MealDetailsState(
    val internalId: Long = 0,
    val recipe: Recipe = Recipe(),
    var servings: Int? = 1,
    var cookColor: CookColor = CookColor.TRANSPARENT,
    val cookingDate: Date = Date(),
    val shoppingListCreatedAt: Date? = null,
) {
    var servingsRatio: Float = ((servings ?: 1) / (recipe.defaultServings ?: 1).toFloat())

    constructor(meal: Meal /*= MealMock.generateSingleObject()*/) : this(
        internalId = meal.internalId,
        recipe = meal.recipe,
        servings = meal.servings,
        cookColor = meal.cookColor,
        cookingDate = meal.cookingDate,
        shoppingListCreatedAt = meal.shoppingListCreatedAt
    )
}
