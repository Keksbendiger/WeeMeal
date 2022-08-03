package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.BaseModel
import de.fhe.ai.weemeal.domain.formats.QuantityFormat

data class Ingredient(
    override var internalId: Long = 0,
    var name: String,
    val image: Int = de.fhe.ai.weemeal.domain.R.drawable.ingredients_placeholde,
    var quantity: QuantityFormat = QuantityFormat(quantity = 0.0f, unit = "ml"),
) : BaseModel {

    fun equalsWithoutId(other: Any?): Boolean {
        return (
            (other is Ingredient) &&
                other.name == this.name &&
                other.image == this.image &&
                other.quantity == this.quantity
            )
    }
    companion object {
        fun List<Ingredient>.areIngredientListsEqualWithoutId(other: List<Ingredient>?): Boolean {
            this.forEachIndexed { index, thisItem ->
                if (!thisItem.equalsWithoutId(other?.get(index))) {
                    return false
                }
            }
            return true
        }
    }
}
