package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.BaseModel
import de.fhe.ai.weemeal.domain.enums.CookColor
import java.util.Date

data class Meal(
    override var internalId: Long = 0,
    val recipe: Recipe,
    var servings: Int? = 1,
    val cookColor: CookColor? = CookColor.TRANSPARENT,
    val cookingDate: Date,
    val shoppingListCreatedAt: Date? = null,
): BaseModel
