package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.BaseModel
import de.fhe.ai.weemeal.domain.R
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.domain.models.Ingredient.Companion.areIngredientListsEqualWithoutId
import de.fhe.ai.weemeal.domain.models.Tag.Companion.areTagListsEqualWithoutId

data class Recipe(
    override var internalId: Long = 0,
    val name: String,
    var defaultServings: Int? = 1,
    var defaultIngredients: List<Ingredient>? = listOf(),
    val timePreparation: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
    val timeActiveCooking: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
    val timeOverall: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
    val instructions: String? = "",
    val image: Int = R.drawable.recipe_placeholder,
    var tags: List<Tag>? = Tag.generateDefaultTagList(),
) : BaseModel {

    fun equalsWithoutId(other: Any?): Boolean {

        return (
            (other is Recipe) &&
                other.name == this.name &&
                other.defaultServings == this.defaultServings &&
                other.timePreparation == this.timePreparation &&
                other.timeActiveCooking == this.timeActiveCooking &&
                other.timeOverall == this.timeOverall &&
                other.instructions == this.instructions &&
                other.image == this.image &&
                other.defaultIngredients!!.areIngredientListsEqualWithoutId(this.defaultIngredients) &&
                other.tags!!.areTagListsEqualWithoutId(this.tags)
            )
    }

    companion object {
        fun List<Recipe>.areRecipeListsEqualWithoutId(other: List<Recipe>?): Boolean {
            this.forEachIndexed { index, thisItem ->
                if (!thisItem.equalsWithoutId(other?.get(index))) {
                    return false
                }
            }
            return true
        }
    }
}
