package de.darthkali.domain.models

import de.darthkali.domain.models.enums.CookColor
import java.util.Date

data class Meal(
    val recipe: Recipe,
    val servings: Int? = 1,
    val cookColor: CookColor? = CookColor.TRANSPARENT,
    val cookingDate: Date,
    val shoppingListCreatedAt: Date? = null,
)