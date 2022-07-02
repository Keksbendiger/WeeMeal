package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.BaseModel
import de.fhe.ai.weemeal.domain.enums.CookColor
import java.time.LocalDate
import java.util.Date

data class Meal(
    override var internalId: Long = 0,
    var recipe: Recipe,
    var servings: Int? = 1,
    val cookColor: CookColor? = CookColor.TRANSPARENT,
    val cookingDate: Date,
    val shoppingListCreatedAt: Date? = null,
) : BaseModel {

    fun equalsWithoutId(other: Any?): Boolean {

        return (
            (other is Meal) &&
                other.recipe!!.equalsWithoutId(this.recipe) &&
                other.servings == this.servings &&
                other.cookColor == this.cookColor &&
                other.cookingDate == this.cookingDate &&
                other.shoppingListCreatedAt == this.shoppingListCreatedAt
            )
    }

    override fun toString(): String {
        return StringBuilder()
            .append("|internalId: ")
            .append(this.internalId)
            .append("| recipe: ")
            .append(this.recipe)
            .append("| servings: ")
            .append(this.servings)
            .append("| cookColor: ")
            .append(this.cookColor)
            .append("| cookingDate: ")
            .append(this.cookingDate)
            .append("| description: ")
            .append(this.shoppingListCreatedAt)
            .append("| shoppingListCreatedAt: ")
            .toString()
    }

    companion object {
        fun List<Meal>.areRecipeListsEqualWithoutId(other: List<Meal>?): Boolean {
            this.forEachIndexed { index, thisItem ->
                if (!thisItem.equalsWithoutId(other?.get(index))) {
                    return false
                }
            }
            return true
        }
    }
}
