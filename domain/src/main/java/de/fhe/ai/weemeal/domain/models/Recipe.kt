package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.enums.MealTime
import de.fhe.ai.weemeal.domain.formats.TimeFormat

data class Recipe(
    val name: String,
    val defaultServings: Int? = 1,
    val defaultIngredients: List<Ingredient>? = listOf(),
    val timePreparation: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"), // TODO: TimeFormat? in Common?
    val timeActiveCooking: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"), // TODO: TimeFormat?
    val timeOverall: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"), // TODO: TimeFormat?
    val instructions: String? = "",
    val image: String? = "",
    val seasonality: List<String>? = listOf(), // TODO: Liste?
    val mealTime: List<MealTime>? = listOf(), // TODO: Liste?
    val optionalTags: List<Tag>? = listOf(), // TODO: Liste?
//    -nutritionalValue : ?,
)
