package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.BaseModel
import de.fhe.ai.weemeal.domain.models.Ingredient.Companion.areIngredientListsEqualWithoutId
import de.fhe.ai.weemeal.domain.models.Tag.Companion.areTagListsEqualWithoutId

data class ShoppingList(
    override var internalId: Long = 0,
    val items: List<Ingredient>
) : BaseModel {

    fun equalsWithoutId(other: Any?): Boolean {

        return (
                (other is ShoppingList) &&
                        other.items.areIngredientListsEqualWithoutId(this.items)
                )
    }
}
