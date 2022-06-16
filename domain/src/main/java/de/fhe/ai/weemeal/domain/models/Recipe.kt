package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.BaseModel
import de.fhe.ai.weemeal.domain.formats.TimeFormat

data class Recipe(
    override var internalId: Long = 0,
    val name: String,
    val defaultServings: Int? = 1,
    var defaultIngredients: List<Ingredient>? = listOf(),
    val timePreparation: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
    val timeActiveCooking: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
    val timeOverall: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
    val instructions: String? = "",
    val image: String? = "",
    val tags: List<Tag>? = Tag.generateDefaultTagList(),
): BaseModel
