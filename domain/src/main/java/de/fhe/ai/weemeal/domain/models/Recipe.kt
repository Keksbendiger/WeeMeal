package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.domain.formats.TimeUnit

data class Recipe(
    val name: String,
    val defaultServings: Int? = 1,
    val defaultIngredients: List<Ingredient>? = listOf(),
    val timePreparation: TimeFormat? = TimeFormat(value = 0.0f, unit = TimeUnit.HOURS),
    val timeActiveCooking: TimeFormat? = TimeFormat(value = 0.0f, unit = TimeUnit.HOURS),
    val timeOverall: TimeFormat? = TimeFormat(value = 0.0f, unit = TimeUnit.HOURS),
    val instructions: String? = "",
    val image: String? = "",
    val tags: List<Tag>? = Tag.generateDefaultTagList(),
)
