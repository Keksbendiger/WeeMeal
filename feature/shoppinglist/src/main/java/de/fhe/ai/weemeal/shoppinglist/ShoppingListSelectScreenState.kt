package de.fhe.ai.weemeal.shoppinglist

import de.fhe.ai.weemeal.domain.enums.CookColor
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.domain.models.ShoppingList

data class ShoppingListSelectScreenState(var shoppingList: ShoppingList)

data class MealOnlyForView(
    var meal: Meal,
    var borderColor: CookColor = CookColor.TRANSPARENT,
    var selected: Boolean = false
)

data class MealOnlyForViewWithCounter(
    var mealOnlyForViewList: MutableList<MealOnlyForView>,
    var counter: Int = 0
)