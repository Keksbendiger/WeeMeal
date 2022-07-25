package de.fhe.ai.weemeal.recipeDetail

import de.fhe.ai.weemeal.domain.R
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.domain.models.Tag
import de.fhe.ai.weemeal.mocks.RecipeMock

data class RecipeEditState(
    var internalId: Long = 0,
    var name: String = "",
    var defaultServings: Int? = 1,
    var defaultIngredients: MutableList<Ingredient>? = mutableListOf(),
    var timePreparation: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
    var timeActiveCooking: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
    var timeOverall: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
    var instructions: String? = "",
    var image: Int = R.drawable.recipe_placeholder,
    var tags: MutableList<Tag>? = Tag.generateDefaultTagList().toMutableList(),
    var counter: Int = 0
) {
    constructor(recipe: Recipe /*= RecipeMock.generateSingleObject()*/) : this(
        internalId = recipe.internalId,
        name = recipe.name,
        defaultServings = recipe.defaultServings,
        defaultIngredients = recipe.defaultIngredients?.toMutableList(),
        timePreparation = recipe.timePreparation,
        timeActiveCooking = recipe.timeActiveCooking,
        timeOverall = recipe.timeOverall,
        instructions = recipe.instructions,
        image = recipe.image,
        tags = recipe.tags?.toMutableList()
    )

    fun convertToRecipe(): Recipe {
        return Recipe(
            internalId,
            name,
            defaultServings,
            defaultIngredients,
            timePreparation,
            timeActiveCooking,
            timeOverall,
            instructions,
            image,
            tags
        )
    }

    fun loadRecipe(recipe: Recipe) {
        internalId = recipe.internalId
        name = recipe.name
        defaultServings = recipe.defaultServings
        defaultIngredients = recipe.defaultIngredients?.toMutableList()
        timePreparation = recipe.timePreparation
        timeActiveCooking = recipe.timeActiveCooking
        timeOverall = recipe.timeOverall
        instructions = recipe.instructions
        image = recipe.image
        tags = recipe.tags?.toMutableList()
    }
}
