package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.BaseModel
import de.fhe.ai.weemeal.domain.R
import de.fhe.ai.weemeal.domain.formats.TimeFormat

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
    val tags: List<Tag>? = Tag.generateDefaultTagList(),
) : BaseModel
